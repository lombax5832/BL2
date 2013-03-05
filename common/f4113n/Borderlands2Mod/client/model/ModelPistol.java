// Date: 1/26/2013 9:28:33 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package f4113n.Borderlands2Mod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelPistol extends ModelBase {
	// fields
	ModelRenderer Handle;
	ModelRenderer Shape1;
	ModelRenderer Shape2;

	public ModelPistol() {
		textureWidth = 32;
		textureHeight = 32;

		Handle = new ModelRenderer(this, 0, 12);
		Handle.addBox(-1F, 0F, -1F, 2, 6, 2);
		Handle.setRotationPoint(0F, 18F, 0F);
		Handle.setTextureSize(64, 32);
		Handle.mirror = true;
		setRotation(Handle, 0F, 0F, 0F);
		Shape1 = new ModelRenderer(this, 0, 0);
		Shape1.addBox(-1F, -4F, -7F, 2, 4, 8);
		Shape1.setRotationPoint(0F, 18F, 0F);
		Shape1.setTextureSize(64, 32);
		Shape1.mirror = true;
		setRotation(Shape1, 0F, 0F, 0F);
		Shape2 = new ModelRenderer(this, 20, 0);
		Shape2.addBox(-0.5F, -3.5F, -9F, 1, 1, 2);
		Shape2.setRotationPoint(0F, 18F, 0F);
		Shape2.setTextureSize(64, 32);
		Shape2.mirror = true;
		setRotation(Shape2, 0F, 0F, 0F);
	}

	public void render(Entity entity, float f, float f1, float f2, float f3,float f4, float f5) {
		super.render(entity, f, f1, f2, f3, f4, f5);
		Handle.render(f5);
		Shape1.render(f5);
		Shape2.render(f5);
	}

	private void setRotation(ModelRenderer model, float x, float y, float z) {
		model.rotateAngleX = x;
		model.rotateAngleY = 0.7F;
		model.rotateAngleZ = 40.8F;
	}

	public void render(float scale) {

		Handle.render(scale);
        Shape1.render(scale);
        Shape2.render(scale);
    }
}
