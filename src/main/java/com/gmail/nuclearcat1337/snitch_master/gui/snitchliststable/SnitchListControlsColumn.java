package com.gmail.nuclearcat1337.snitch_master.gui.snitchliststable;

import com.gmail.nuclearcat1337.snitch_master.SnitchMaster;
import com.gmail.nuclearcat1337.snitch_master.gui.GuiConstants;
import com.gmail.nuclearcat1337.snitch_master.gui.tables.TableColumn;
import com.gmail.nuclearcat1337.snitch_master.snitches.SnitchList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;

import java.util.List;

/**
 * Created by Mr_Little_Kitty on 1/2/2017.
 */
public class SnitchListControlsColumn implements TableColumn<SnitchList>
{
    private static final int ARROW_BUTTON_WIDTH = 20;
    private static final int ON_OFF_BUTTON_WIDTH = 30;
    private static final int ENTRY_WIDTH = ARROW_BUTTON_WIDTH + GuiConstants.SMALL_SEPARATION_DISTANCE + ON_OFF_BUTTON_WIDTH + GuiConstants.SMALL_SEPARATION_DISTANCE + ARROW_BUTTON_WIDTH;

    private static Minecraft mc;

    private final SnitchListsTable table;

    public SnitchListControlsColumn(SnitchListsTable table)
    {
        mc = Minecraft.getMinecraft();
        this.table = table;
    }

    @Override
    public GuiButton[] prepareEntry(SnitchList list)
    {
        GuiButton[] buttons = new GuiButton[3];
        buttons[0] = new GuiButton(0, 0, 0, ARROW_BUTTON_WIDTH, GuiConstants.STANDARD_BUTTON_HEIGHT, "^");
        buttons[1] = new GuiButton(1, 0, 0, ON_OFF_BUTTON_WIDTH, GuiConstants.STANDARD_BUTTON_HEIGHT, list.shouldRenderSnitches() ? "On" : "Off");
        buttons[2] = new GuiButton(2,0, 0, ARROW_BUTTON_WIDTH, GuiConstants.STANDARD_BUTTON_HEIGHT, "v");
        return buttons;
    }

    @Override
    public String getColumnName()
    {
        return "Controls";
    }

    @Override
    public boolean doBoundsCheck()
    {
        return true;
    }

    @Override
    public void clicked(SnitchList list, boolean leftClick, int xPos, int yPos, GuiButton[] buttons, GuiScreen parentScreen,int slotIndex)
    {
        //Don't allow right clicks
        if(!leftClick)
            return;

        if(buttons[0].mousePressed(mc,xPos,yPos)) //Up arrow button
        {
            table.swapTableItems(slotIndex,slotIndex-1);
            renderIndices();
        }
        else if(buttons[1].mousePressed(mc,xPos,yPos)) //Render toggle button
        {
            list.setShouldRenderSnitches(!list.shouldRenderSnitches());
        }
        else if(buttons[2].mousePressed(mc,xPos,yPos)) //Down arrow button
        {
            table.swapTableItems(slotIndex,slotIndex+1);
            renderIndices();
        }
    }

    private void renderIndices()
    {
        for(int i = 0; i < table.getTableSize(); i++)
            table.getTableItem(i).setRenderPriority(i+1);

        SnitchMaster.instance.refreshSnitchListPriorities();
        SnitchMaster.instance.getSnitchLists().sortSnitchLists();
    }

    @Override
    public void released(SnitchList list, int xPos, int yPos, GuiButton[] buttons, GuiScreen parentScreen,int slotIndex)
    {
        buttons[0].mouseReleased(xPos,yPos);
        buttons[1].mouseReleased(xPos,yPos);
        buttons[2].mouseReleased(xPos,yPos);
    }

    @Override
    public void draw(SnitchList list, int xPosition, int yPosition, int columnWidth, int slotHeight, GuiButton[] buttons,int slotIndex, int mouseX, int mouseY)
    {
        yPosition = yPosition + ((slotHeight - GuiConstants.STANDARD_BUTTON_HEIGHT) /2);

        //Set the y position of all 3 buttons
        buttons[0].yPosition = yPosition;
        buttons[1].yPosition = yPosition;
        buttons[2].yPosition = yPosition;

        int xPos = xPosition + (columnWidth/2) - (ENTRY_WIDTH/2);

        buttons[0].xPosition = xPos;

        xPos += buttons[0].width + GuiConstants.SMALL_SEPARATION_DISTANCE;

        buttons[1].displayString = list.shouldRenderSnitches() ? "On" : "Off";
        buttons[1].xPosition = xPos;

        xPos += buttons[1].width + GuiConstants.SMALL_SEPARATION_DISTANCE;

        buttons[2].xPosition = xPos;

        buttons[0].drawButton(mc,mouseX,mouseY);
        buttons[1].drawButton(mc,mouseX,mouseY);
        buttons[2].drawButton(mc,mouseX,mouseY);
    }

    @Override
    public int getDrawWidth(SnitchList list)
    {
        return ENTRY_WIDTH;
    }

    @Override
    public List<String> hover(SnitchList item, int xPos, int yPos)
    {
        return null;
    }

    @Override
    public int compare(SnitchList o1, SnitchList o2)
    {
        return 0;
    }
}
