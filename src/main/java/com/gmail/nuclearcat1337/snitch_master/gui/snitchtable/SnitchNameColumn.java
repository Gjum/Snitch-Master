package com.gmail.nuclearcat1337.snitch_master.gui.snitchtable;

import com.gmail.nuclearcat1337.snitch_master.gui.tables.TableColumn;
import com.gmail.nuclearcat1337.snitch_master.snitches.Snitch;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

/**
 * Created by Mr_Little_Kitty on 12/31/2016.
 */
public class SnitchNameColumn implements TableColumn<Snitch>
{
    private static Minecraft mc;

    public SnitchNameColumn()
    {
        mc = Minecraft.getMinecraft();
    }

    @Override
    public GuiButton[] prepareEntry(Snitch item)
    {
        return null;
    }

    @Override
    public String getColumnName()
    {
        return "Snitch Name";
    }

    @Override
    public boolean doBoundsCheck()
    {
        return false;
    }

    @Override
    public void clicked(Snitch item, boolean leftClick, int xPos, int yPos, GuiButton[] buttons, GuiScreen parentScreen, int slotIndex)
    {

    }

    @Override
    public void released(Snitch item, int xPos, int yPos, GuiButton[] buttons, GuiScreen parentScreen, int slotIndex)
    {

    }

    @Override
    public void draw(Snitch snitch, int xPos, int yPos, int columnWidth, int slotHeight, GuiButton[] buttons, int slotIndex, int mouseX, int mouseY)
    {
        String text = snitch.getSnitchName().isEmpty() ? "Undefined" : snitch.getSnitchName();
        int yFinal = yPos + ((slotHeight - mc.fontRenderer.FONT_HEIGHT) / 2);
        int nameWidth = mc.fontRenderer.getStringWidth(text);
        int namePos = xPos + (columnWidth / 2) - (nameWidth / 2);
        mc.fontRenderer.drawString(text, namePos, yFinal, 16777215);
    }

    @Override
    public int getDrawWidth(Snitch snitch)
    {
        String text = snitch.getSnitchName().isEmpty() ? "Undefined" : snitch.getSnitchName();
        return mc.fontRenderer.getStringWidth(text);
    }

    @Override
    public List<String> hover(Snitch snitch, int xPos, int yPos)
    {
        //Were no longer going to show the attached snitch lists on hover. Instead we will have a button in a different column
        //        List<String> temp = new ArrayList<>(snitch.getAttachedSnitchLists().size()+1);
        //        temp.add("Snitch Lists:");
        //        for(SnitchList list : snitch.getAttachedSnitchLists())
        //            temp.add(list.getListName());
        //        return temp;
        return null;
    }

    @Override
    public boolean canSort()
    {
        return true;
    }

    @Override
    public int compare(Snitch snitch, Snitch other)
    {
        String one = snitch.getSnitchName().isEmpty() ? "Undefined" : snitch.getSnitchName();
        String two = other.getSnitchName().isEmpty() ? "Undefined" : other.getSnitchName();

        return one.compareTo(two);
    }
}
