package com.mjr.extraplanets.client.render.entities.monsters;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.MathHelper;
import net.minecraft.util.ResourceLocation;

import org.lwjgl.opengl.GL11;

import com.mjr.extraplanets.Constants;
import com.mjr.extraplanets.client.model.monsters.ModelBlueCreeper;
import com.mjr.extraplanets.entities.monsters.EntityBlueCreeper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class RenderEvolvedBlueCreeper extends RenderLiving {
	private static final ResourceLocation armoBlueCreeperTextures = new ResourceLocation(Constants.ASSET_PREFIX, "textures/entity/creeper/creeper_armor.png");
	private static final ResourceLocation creeperTextures = new ResourceLocation(Constants.ASSET_PREFIX, "textures/entity/creeper/blue_creeper.png");
	/** The creeper model. */
	private ModelBase creeperModel = new ModelBlueCreeper(2.0F);

	public RenderEvolvedBlueCreeper() {
		super(new ModelBlueCreeper(), 0.5F);
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendeBlue. Args: entityLiving, partialTickTime
	 */
	protected void preRenderCallback(EntityBlueCreeper p_77041_1_, float p_77041_2_) {
		float f1 = p_77041_1_.getCreeperFlashIntensity(p_77041_2_);
		float f2 = 1.0F + MathHelper.sin(f1 * 100.0F) * f1 * 0.01F;

		if (f1 < 0.0F) {
			f1 = 0.0F;
		}

		if (f1 > 1.0F) {
			f1 = 1.0F;
		}

		f1 *= f1;
		f1 *= f1;
		float f3 = (1.0F + f1 * 0.4F) * f2;
		float f4 = (1.0F + f1 * 0.1F) / f2;
		GL11.glScalef(f3, f4, f3);
	}

	/**
	 * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
	 */
	protected int getColorMultiplier(EntityBlueCreeper p_77030_1_, float p_77030_2_, float p_77030_3_) {
		float f2 = p_77030_1_.getCreeperFlashIntensity(p_77030_3_);

		if ((int) (f2 * 10.0F) % 2 == 0) {
			return 0;
		} else {
			int i = (int) (f2 * 0.2F * 255.0F);

			if (i < 0) {
				i = 0;
			}

			if (i > 255) {
				i = 255;
			}

			short short1 = 255;
			short short2 = 255;
			short short3 = 255;
			return i << 24 | short1 << 16 | short2 << 8 | short3;
		}
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	protected int shouldRenderPass(EntityBlueCreeper p_77032_1_, int p_77032_2_, float p_77032_3_) {
		if (p_77032_1_.getPowered()) {
			if (p_77032_1_.isInvisible()) {
				GL11.glDepthMask(false);
			} else {
				GL11.glDepthMask(true);
			}

			if (p_77032_2_ == 1) {
				float f1 = p_77032_1_.ticksExisted + p_77032_3_;
				this.bindTexture(armoBlueCreeperTextures);
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				float f2 = f1 * 0.01F;
				float f3 = f1 * 0.01F;
				GL11.glTranslatef(f2, f3, 0.0F);
				this.setRenderPassModel(this.creeperModel);
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_BLEND);
				float f4 = 0.5F;
				GL11.glColor4f(f4, f4, f4, 1.0F);
				GL11.glDisable(GL11.GL_LIGHTING);
				GL11.glBlendFunc(GL11.GL_ONE, GL11.GL_ONE);
				return 1;
			}

			if (p_77032_2_ == 2) {
				GL11.glMatrixMode(GL11.GL_TEXTURE);
				GL11.glLoadIdentity();
				GL11.glMatrixMode(GL11.GL_MODELVIEW);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glDisable(GL11.GL_BLEND);
			}
		}

		return -1;
	}

	protected int inheritRenderPass(EntityBlueCreeper p_77035_1_, int p_77035_2_, float p_77035_3_) {
		return -1;
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	protected ResourceLocation getEntityTexture(EntityBlueCreeper p_110775_1_) {
		return creeperTextures;
	}

	/**
	 * Allows the render to do any OpenGL state modifications necessary before the model is rendeBlue. Args: entityLiving, partialTickTime
	 */
	@Override
	protected void preRenderCallback(EntityLivingBase p_77041_1_, float p_77041_2_) {
		this.preRenderCallback((EntityBlueCreeper) p_77041_1_, p_77041_2_);
	}

	/**
	 * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
	 */
	@Override
	protected int getColorMultiplier(EntityLivingBase p_77030_1_, float p_77030_2_, float p_77030_3_) {
		return this.getColorMultiplier((EntityBlueCreeper) p_77030_1_, p_77030_2_, p_77030_3_);
	}

	/**
	 * Queries whether should render the specified pass or not.
	 */
	@Override
	protected int shouldRenderPass(EntityLivingBase p_77032_1_, int p_77032_2_, float p_77032_3_) {
		return this.shouldRenderPass((EntityBlueCreeper) p_77032_1_, p_77032_2_, p_77032_3_);
	}

	@Override
	protected int inheritRenderPass(EntityLivingBase p_77035_1_, int p_77035_2_, float p_77035_3_) {
		return this.inheritRenderPass((EntityBlueCreeper) p_77035_1_, p_77035_2_, p_77035_3_);
	}

	/**
	 * Returns the location of an entity's texture. Doesn't seem to be called unless you call Render.bindEntityTexture.
	 */
	@Override
	protected ResourceLocation getEntityTexture(Entity p_110775_1_) {
		return this.getEntityTexture((EntityBlueCreeper) p_110775_1_);
	}
}