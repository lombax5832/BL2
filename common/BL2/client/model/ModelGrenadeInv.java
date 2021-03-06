// Date: 1/25/2013 7:15:38 PM
// Template version 1.1
// Java generated by Techne
// Keep in mind that you still need to fill in some blanks
// - ZeuX

package BL2.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelGrenadeInv extends ModelBase {
    // fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;

    public ModelGrenadeInv() {
        textureWidth = 64;
        textureHeight = 32;

        Shape1 = new ModelRenderer(this, 0, 0);
        Shape1.addBox(-1.533333F, -2F, -1.5F, 3, 4, 3);
        Shape1.setRotationPoint(0F, 20F, 0F);
        Shape1.setTextureSize(64, 32);
        Shape1.mirror = true;
        setRotation(Shape1, 0F, 0F, 0F);
        Shape2 = new ModelRenderer(this, 12, 0);
        Shape2.addBox(-0.5F, -2.5F, -2F, 1, 2, 2);
        Shape2.setRotationPoint(0F, 20F, 0F);
        Shape2.setTextureSize(64, 32);
        Shape2.mirror = true;
        setRotation(Shape2, 0F, 0F, 0F);
    }

    public void render() {
        Shape1.render(.0625F);
        Shape2.render(.0625F);
    }

    public void render(float scale) {
        Shape1.render(scale);
        Shape2.render(scale);
    }

    private void setRotation(ModelRenderer model, float x, float y, float z) {
        model.rotateAngleX = 3.2F;
        model.rotateAngleY = 1.5F;
        model.rotateAngleZ = z;
    }

    @Override
    public void setRotationAngles(float par1, float par2, float par3,
            float par4, float par5, float par6, Entity par7Entity) {
        super.setRotationAngles(par1, par2, par3, par4, par5, par6, par7Entity);
    }

}
