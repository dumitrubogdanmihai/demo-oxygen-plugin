package ro.sync.demo;

import java.net.URL;

import ro.sync.exml.plugin.workspace.WorkspaceAccessPluginExtension;
import ro.sync.exml.workspace.api.PluginWorkspace;
import ro.sync.exml.workspace.api.editor.WSEditor;
import ro.sync.exml.workspace.api.editor.page.WSEditorPage;
import ro.sync.exml.workspace.api.editor.page.author.WSAuthorEditorPage;
import ro.sync.exml.workspace.api.listeners.WSEditorChangeListener;
import ro.sync.exml.workspace.api.listeners.WSEditorPageChangedListener;
import ro.sync.exml.workspace.api.standalone.StandalonePluginWorkspace;

public class WorkspaceAccessImpl implements WorkspaceAccessPluginExtension {

  public WorkspaceAccessImpl() {
    System.out.println("new WorkspaceAccessImpl");
  }
  
  public void applicationStarted(final StandalonePluginWorkspace pluginWorkspaceAccess) {
    pluginWorkspaceAccess.addEditorChangeListener(
      new WSEditorChangeListener() {
        @Override
        public void editorOpened(URL editorUrl) {
          WSEditor editorAccess = pluginWorkspaceAccess.getEditorAccess(editorUrl, PluginWorkspace.MAIN_EDITING_AREA);
          WSEditorPage currentPage = editorAccess.getCurrentPage();
          if (currentPage instanceof WSAuthorEditorPage) {
            WSAuthorEditorPage authorEditorPage = (WSAuthorEditorPage) currentPage;
            authorEditorPage.getAuthorAccess().getDocumentController().setDocumentFilter(
                new FunkyDocFilter());
          }
          // It's also a good idea to listener for page changes on the editor.
          // Perhaps the editor opens in the text page and the user switches later on to author.
          editorAccess.addPageChangedListener(new WSEditorPageChangedListener() {
            public void editorPageChanged() {
              // Same code here to add the filter.
            }
          });
        }
      }, PluginWorkspace.MAIN_EDITING_AREA);
    
  }

  public boolean applicationClosing() {
    return false;
  }
}
