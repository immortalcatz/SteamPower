package steamcraft.steamcraft.client.render;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelResearchTable extends ModelBase
{
  //fields
    ModelRenderer Leg2;
    ModelRenderer Leg1;
    ModelRenderer Leg4;
    ModelRenderer Leg3;
    ModelRenderer Tabletop;
    ModelRenderer Bar1;
    ModelRenderer Bar3;
    ModelRenderer Book;
    ModelRenderer InkBottle;
    ModelRenderer InkTop;
    ModelRenderer Bar2;
    ModelRenderer Bar4;
    ModelRenderer Paper;

  public ModelResearchTable()
  {
    textureWidth = 64;
    textureHeight = 48;

      Leg2 = new ModelRenderer(this, 0, 18);
      Leg2.addBox(0F, 0F, 0F, 3, 14, 3);
      Leg2.setRotationPoint(-7F, 10F, 4F);
      Leg2.setTextureSize(64, 48);
      Leg2.mirror = true;
      setRotation(Leg2, 0F, 0F, 0F);
      Leg1 = new ModelRenderer(this, 0, 18);
      Leg1.addBox(0F, 0F, 0F, 3, 14, 3);
      Leg1.setRotationPoint(4F, 10F, 4F);
      Leg1.setTextureSize(64, 48);
      Leg1.mirror = true;
      setRotation(Leg1, 0F, 0F, 0F);
      Leg4 = new ModelRenderer(this, 0, 18);
      Leg4.addBox(0F, 0F, 0F, 3, 14, 3);
      Leg4.setRotationPoint(4F, 10F, -7F);
      Leg4.setTextureSize(64, 48);
      Leg4.mirror = true;
      setRotation(Leg4, 0F, 0F, 0F);
      Leg3 = new ModelRenderer(this, 0, 18);
      Leg3.addBox(0F, 0F, 0F, 3, 14, 3);
      Leg3.setRotationPoint(-7F, 10F, -7F);
      Leg3.setTextureSize(64, 48);
      Leg3.mirror = true;
      setRotation(Leg3, 0F, 0F, 0F);
      Tabletop = new ModelRenderer(this, 0, 0);
      Tabletop.addBox(0F, 0F, 0F, 16, 2, 16);
      Tabletop.setRotationPoint(-8F, 8F, -8F);
      Tabletop.setTextureSize(64, 48);
      Tabletop.mirror = true;
      setRotation(Tabletop, 0F, 0F, 0F);
      Bar1 = new ModelRenderer(this, 12, 18);
      Bar1.addBox(0F, 0F, 0F, 8, 2, 3);
      Bar1.setRotationPoint(-4F, 15F, 4F);
      Bar1.setTextureSize(64, 48);
      Bar1.mirror = true;
      setRotation(Bar1, 0F, 0F, 0F);
      Bar3 = new ModelRenderer(this, 12, 18);
      Bar3.addBox(0F, -1.266667F, 0F, 8, 2, 3);
      Bar3.setRotationPoint(-4F, 16F, -7F);
      Bar3.setTextureSize(64, 48);
      Bar3.mirror = true;
      setRotation(Bar3, 0F, 0F, 0F);
      Book = new ModelRenderer(this, 34, 18);
      Book.addBox(0F, 0F, 0F, 4, 1, 5);
      Book.setRotationPoint(1F, 7F, -4.5F);
      Book.setTextureSize(64, 48);
      Book.mirror = true;
      setRotation(Book, 0F, 0.4415993F, 0F);
      InkBottle = new ModelRenderer(this, 12, 23);
      InkBottle.addBox(-1F, 0F, -1F, 2, 2, 2);
      InkBottle.setRotationPoint(-5F, 6F, -5F);
      InkBottle.setTextureSize(64, 48);
      InkBottle.mirror = true;
      setRotation(InkBottle, 0F, -0.3316126F, 0F);
      InkTop = new ModelRenderer(this, 20, 23);
      InkTop.addBox(-0.5F, 0F, -0.5F, 1, 1, 1);
      InkTop.setRotationPoint(-5F, 5F, -5F);
      InkTop.setTextureSize(64, 48);
      InkTop.mirror = true;
      setRotation(InkTop, -0.0174533F, -0.3316126F, 0F);
      Bar2 = new ModelRenderer(this, 12, 18);
      Bar2.addBox(0F, 0F, -1F, 8, 2, 3);
      Bar2.setRotationPoint(5F, 15F, 4F);
      Bar2.setTextureSize(64, 48);
      Bar2.mirror = true;
      setRotation(Bar2, 0F, 1.570796F, 0F);
      Bar4 = new ModelRenderer(this, 12, 18);
      Bar4.addBox(0F, 0F, 0F, 8, 2, 3);
      Bar4.setRotationPoint(-7F, 15F, 4F);
      Bar4.setTextureSize(64, 48);
      Bar4.mirror = true;
      setRotation(Bar4, 0F, 1.570796F, 0F);
      Paper = new ModelRenderer(this, 34, 24);
      Paper.addBox(0F, 0.4666667F, 0F, 5, 0, 6);
      Paper.setRotationPoint(-3F, 7.5F, -1F);
      Paper.setTextureSize(64, 48);
      Paper.mirror = true;
      setRotation(Paper, 0F, -0.2974289F, 0F);
  }

  @Override
public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5);
    Leg2.render(f5);
    Leg1.render(f5);
    Leg4.render(f5);
    Leg3.render(f5);
    Tabletop.render(f5);
    Bar1.render(f5);
    Bar3.render(f5);
    Book.render(f5);
    InkBottle.render(f5);
    InkTop.render(f5);
    Bar2.render(f5);
    Bar4.render(f5);
    Paper.render(f5);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

public void renderModel(float f5) {
	 Leg2.render(f5);
	    Leg1.render(f5);
	    Leg4.render(f5);
	    Leg3.render(f5);
	    Tabletop.render(f5);
	    Bar1.render(f5);
	    Bar3.render(f5);
	    Book.render(f5);
	    InkBottle.render(f5);
	    InkTop.render(f5);
	    Bar2.render(f5);
	    Bar4.render(f5);
	    Paper.render(f5);

}

}
