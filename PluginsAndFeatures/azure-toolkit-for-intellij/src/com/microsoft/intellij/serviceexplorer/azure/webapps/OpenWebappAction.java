package com.microsoft.intellij.serviceexplorer.azure.webapps;


import com.microsoft.azure.management.appservice.WebApp;
import com.microsoft.intellij.util.PluginUtil;
import com.microsoft.tooling.msservices.helpers.Name;
import com.microsoft.tooling.msservices.serviceexplorer.NodeActionEvent;
import com.microsoft.tooling.msservices.serviceexplorer.NodeActionListener;
import com.microsoft.tooling.msservices.serviceexplorer.azure.webapp.WebAppNode;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;

import static com.microsoft.intellij.ui.messages.AzureBundle.message;

@Name("Open in Browser")
public class OpenWebappAction extends NodeActionListener {

    private WebAppNode webAppNode;

    public OpenWebappAction(WebAppNode webappNode) {
        this.webAppNode = webappNode;
    }

    @Override
    public void actionPerformed(NodeActionEvent e) {
        WebApp webApp = webAppNode.getWebApp();
        try {
            String appServiceLink = "https://" + webApp.defaultHostName();
            Desktop.getDesktop().browse(URI.create(appServiceLink));
        } catch (IOException e1) {
            PluginUtil.displayErrorDialogAndLog(message("error"), message("error"), e1);
        }
    }
}