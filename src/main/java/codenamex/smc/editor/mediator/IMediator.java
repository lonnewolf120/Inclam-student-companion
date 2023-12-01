package codenamex.smc.editor.mediator;

import codenamex.smc.editor.TabSpace;
import codenamex.smc.editor.components.FindReplaceToolBar;
import codenamex.smc.editor.components.MainController;
import codenamex.smc.editor.components.MainMenuBar;

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
