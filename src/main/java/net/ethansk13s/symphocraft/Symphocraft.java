package net.ethansk13s.symphocraft;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreConfiguredFeatures;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.placementmodifier.CountPlacementModifier;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

import net.ethansk13s.symphocraft.blocks.RuneOre;
import net.ethansk13s.symphocraft.core.SymphogearPacket;

public class Symphocraft implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("symphocraft");

	public static final RuneOre RUNE_ORE = new RuneOre(FabricBlockSettings.of(Material.METAL).strength(4.0f).requiresTool());

	private static ConfiguredFeature<?, ?> OVERWORLD_RUNE_ORE_GEN = new ConfiguredFeature(
		Feature.ORE, new OreFeatureConfig(
			OreConfiguredFeatures.STONE_ORE_REPLACEABLES,
			RUNE_ORE.getDefaultState(),
			3));

	public static PlacedFeature OVERWORLD_RUNE_ORE_PLACED = new PlacedFeature(
		RegistryEntry.of(OVERWORLD_RUNE_ORE_GEN),
		Arrays.asList(
			CountPlacementModifier.of(5),
			SquarePlacementModifier.of(),
			HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(32))
		));

	public static final ItemGroup SYMPHOCRAFT_GROUP = FabricItemGroupBuilder.build(
		new Identifier("symphocraft", "general"),
		() -> new ItemStack(RUNE_ORE));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new Identifier("symphocraft", "rune_ore"), RUNE_ORE);
		Registry.register(Registry.ITEM, new Identifier("symphocraft", "rune_ore"), new BlockItem(RUNE_ORE, new Item.Settings().group(SYMPHOCRAFT_GROUP)));

		RegisterItems.register();

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("symphocraft", "overworld_rune_ore"), OVERWORLD_RUNE_ORE_GEN);
		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier("symphocraft", "overworld_rune_ore"), OVERWORLD_RUNE_ORE_PLACED);
		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES,
			RegistryKey.of(Registry.PLACED_FEATURE_KEY,
				new Identifier("symphocraft", "overworld_rune_ore")));


		SymphogearPacket.registerReceivePacket();
	}
}
