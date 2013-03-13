package BL2.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelSniper extends ModelBase
{
  //fields
    ModelRenderer Shape1;
    ModelRenderer Shape2;
    ModelRenderer Shape3;
    ModelRenderer Shape4;
    ModelRenderer Shape5;
    ModelRenderer Shape6;
    ModelRenderer Shape7;
    ModelRenderer Shape8;
    ModelRenderer Shape9;
    ModelRenderer Shape10;
    ModelRenderer Shape11;
    ModelRenderer Shape12;
    ModelRenderer Shape13;
    ModelRenderer Shape14;
    ModelRenderer Shape15;
    ModelRenderer Shape16;
    ModelRenderer Shape17;
    ModelRenderer Shape18;
    ModelRenderer Shape19;
    ModelRenderer Shape20;
    ModelRenderer Shape21;
  
  public ModelSniper()
  {
    textureWidth = 128;
    textureHeight = 64;
    
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(-1F, -0.7F, 5F, 2, 3, 9);
      Shape1.setRotationPoint(0F, 18F, 0F);
      Shape1.setTextureSize(64, 32);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0F, 0F);
      Shape2 = new ModelRenderer(this, 0, 0);
      Shape2.addBox(-0.5F, -0.75F, -20F, 1, 1, 16);
      Shape2.setRotationPoint(0F, 18F, 0F);
      Shape2.setTextureSize(64, 32);
      Shape2.mirror = true;
      setRotation(Shape2, 0F, 0F, 0F);
      Shape3 = new ModelRenderer(this, 0, 0);
      Shape3.addBox(-1F, 0.3F, -5F, 2, 2, 10);
      Shape3.setRotationPoint(0F, 18F, 0F);
      Shape3.setTextureSize(64, 32);
      Shape3.mirror = true;
      setRotation(Shape3, 0F, 0F, 0F);
      Shape4 = new ModelRenderer(this, 0, 0);
      Shape4.addBox(-1F, -2F, -4F, 2, 3, 3);
      Shape4.setRotationPoint(0F, 18F, 0F);
      Shape4.setTextureSize(64, 32);
      Shape4.mirror = true;
      setRotation(Shape4, 0F, 0F, 0F);
      Shape5 = new ModelRenderer(this, 0, 0);
      Shape5.addBox(-0.5F, -0.7F, -1F, 1, 1, 6);
      Shape5.setRotationPoint(0F, 18F, 0F);
      Shape5.setTextureSize(64, 32);
      Shape5.mirror = true;
      setRotation(Shape5, 0F, 0F, 0F);
      Shape6 = new ModelRenderer(this, 0, 0);
      Shape6.addBox(1F, -0.7F, -1F, 4, 1, 5);
      Shape6.setRotationPoint(0F, 18F, 0F);
      Shape6.setTextureSize(64, 32);
      Shape6.mirror = true;
      setRotation(Shape6, 0F, 0F, 0F);
      Shape7 = new ModelRenderer(this, 0, 0);
      Shape7.addBox(0.5F, -1F, -2F, 1, 2, 7);
      Shape7.setRotationPoint(0F, 18F, 0F);
      Shape7.setTextureSize(64, 32);
      Shape7.mirror = true;
      setRotation(Shape7, 0F, 0F, 0F);
      Shape8 = new ModelRenderer(this, 0, 0);
      Shape8.addBox(-1.5F, -0.7F, -23F, 3, 1, 3);
      Shape8.setRotationPoint(0F, 18F, 0F);
      Shape8.setTextureSize(64, 32);
      Shape8.mirror = true;
      setRotation(Shape8, 0F, 0F, 0F);
      Shape9 = new ModelRenderer(this, 0, 0);
      Shape9.addBox(-1F, -0.7F, -20F, 2, 1, 2);
      Shape9.setRotationPoint(0F, 18F, 0F);
      Shape9.setTextureSize(64, 32);
      Shape9.mirror = true;
      setRotation(Shape9, 0F, 0F, 0F);
      Shape10 = new ModelRenderer(this, 0, 0);
      Shape10.addBox(-0.5F, 1F, -10F, 1, 1, 6);
      Shape10.setRotationPoint(0F, 18F, 0F);
      Shape10.setTextureSize(64, 32);
      Shape10.mirror = true;
      setRotation(Shape10, 0F, 0F, 0F);
      Shape11 = new ModelRenderer(this, 0, 0);
      Shape11.addBox(1F, -4F, -4F, 1, 4, 1);
      Shape11.setRotationPoint(0F, 18F, 0F);
      Shape11.setTextureSize(64, 32);
      Shape11.mirror = true;
      setRotation(Shape11, 0F, 0F, 0F);
      Shape12 = new ModelRenderer(this, 0, 0);
      Shape12.addBox(-2F, -4F, -4F, 1, 4, 1);
      Shape12.setRotationPoint(0F, 18F, 0F);
      Shape12.setTextureSize(64, 32);
      Shape12.mirror = true;
      setRotation(Shape12, 0F, 0F, 0F);
      Shape13 = new ModelRenderer(this, 0, 0);
      Shape13.addBox(-2F, -5F, -4F, 4, 1, 1);
      Shape13.setRotationPoint(0F, 18F, 0F);
      Shape13.setTextureSize(64, 32);
      Shape13.mirror = true;
      setRotation(Shape13, 0F, 0F, 0F);
      Shape14 = new ModelRenderer(this, 0, 0);
      Shape14.addBox(-0.5F, 1F, -4F, 1, 5, 1);
      Shape14.setRotationPoint(0F, 18F, 0F);
      Shape14.setTextureSize(64, 32);
      Shape14.mirror = true;
      setRotation(Shape14, 0F, 0F, 0.4537856F);
      Shape15 = new ModelRenderer(this, 0, 0);
      Shape15.addBox(-0.5F, 1F, -4F, 1, 5, 1);
      Shape15.setRotationPoint(0F, 18F, 0F);
      Shape15.setTextureSize(64, 32);
      Shape15.mirror = true;
      setRotation(Shape15, 0F, 0F, -0.4537856F);
      Shape16 = new ModelRenderer(this, 0, 0);
      Shape16.addBox(-0.5F, 2F, 12F, 1, 2, 1);
      Shape16.setRotationPoint(0F, 18F, 0F);
      Shape16.setTextureSize(64, 32);
      Shape16.mirror = true;
      setRotation(Shape16, 0F, 0F, 0F);
      Shape17 = new ModelRenderer(this, 0, 0);
      Shape17.addBox(-0.5F, 3F, 8F, 1, 1, 5);
      Shape17.setRotationPoint(0F, 18F, 0F);
      Shape17.setTextureSize(64, 32);
      Shape17.mirror = true;
      setRotation(Shape17, 0F, 0F, 0F);
      Shape18 = new ModelRenderer(this, 0, 0);
      Shape18.addBox(-0.5F, 3F, 7F, 1, 3, 1);
      Shape18.setRotationPoint(0F, 18F, 0F);
      Shape18.setTextureSize(64, 32);
      Shape18.mirror = true;
      setRotation(Shape18, 0.2268928F, 0F, 0F);
      Shape19 = new ModelRenderer(this, 0, 0);
      Shape19.addBox(-0.5F, 2F, 3F, 1, 4, 1);
      Shape19.setRotationPoint(0F, 18F, 0F);
      Shape19.setTextureSize(64, 32);
      Shape19.mirror = true;
      setRotation(Shape19, 0.1745329F, 0F, 0F);
      Shape20 = new ModelRenderer(this, 0, 0);
      Shape20.addBox(-1F, -1F, -1F, 2, 0, 6);
      Shape20.setRotationPoint(0F, 18F, 0F);
      Shape20.setTextureSize(64, 32);
      Shape20.mirror = true;
      setRotation(Shape20, 0F, 0F, 0F);
      Shape21 = new ModelRenderer(this, 0, 0);
      Shape21.addBox(-3F, -0.7F, 2F, 3, 1, 1);
      Shape21.setRotationPoint(0F, 18F, 0F);
      Shape21.setTextureSize(64, 32);
      Shape21.mirror = true;
      setRotation(Shape21, 0F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    Shape1.render(f5);
    Shape2.render(f5);
    Shape3.render(f5);
    Shape4.render(f5);
    Shape5.render(f5);
    Shape6.render(f5);
    Shape7.render(f5);
    Shape8.render(f5);
    Shape9.render(f5);
    Shape10.render(f5);
    Shape11.render(f5);
    Shape12.render(f5);
    Shape13.render(f5);
    Shape14.render(f5);
    Shape15.render(f5);
    Shape16.render(f5);
    Shape17.render(f5);
    Shape18.render(f5);
    Shape19.render(f5);
    Shape20.render(f5);
    Shape21.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
	  model.rotateAngleX = x;
	  model.rotateAngleY = 0.7F;
	  model.rotateAngleZ = 40.8F;
  }
  
  public void render(float scale) {

	  Shape1.render(scale);
	    Shape2.render(scale);
	    Shape3.render(scale);
	    Shape4.render(scale);
	    Shape5.render(scale);
	    Shape6.render(scale);
	    Shape7.render(scale);
	    Shape8.render(scale);
	    Shape9.render(scale);
	    Shape10.render(scale);
	    Shape11.render(scale);
	    Shape12.render(scale);
	    Shape13.render(scale);
	    Shape14.render(scale);
	    Shape15.render(scale);
	    Shape16.render(scale);
	    Shape17.render(scale);
	    Shape18.render(scale);
	    Shape19.render(scale);
	    Shape20.render(scale);
	    Shape21.render(scale);
  }
}
