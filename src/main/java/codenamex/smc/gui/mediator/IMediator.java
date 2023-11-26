package codenamex.smc.gui.mediator;

import codenamex.smc.gui.TabSpace;
import codenamex.smc.gui.components.FindReplaceToolBar;
import codenamex.smc.gui.components.MainController;
import codenamex.smc.gui.components.MainMenuBar;

import java.nio.file.Path;
import java.util.List;

public interface IMediator {

    void setMenuBar(MainMenuBar mainMenuBar);
    void setTabSpaces(List<TabSpace> tabSpaces);
    void setMainController(MainController mainController);
    void setFindReplaceToolBar(FindReplaceToolBar findReplaceToolBar);

    String getText();
    Path getFilePath();
    boolean isFileSaved();
    boolean shouldExit();
    boolean isMatchCase();
    Mediator.EventBuilder getEventBuilder();

    String getMediatorText();
    Path getMediatorFilePath();

}
