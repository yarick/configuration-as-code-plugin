package org.jenkinsci.plugins.casc.cli;

import hudson.Extension;
import hudson.cli.CLICommand;
import jenkins.model.Jenkins;
import org.jenkinsci.plugins.casc.ConfigurationAsCode;
import org.jenkinsci.plugins.casc.yaml.YamlSource;
import org.kohsuke.accmod.Restricted;
import org.kohsuke.accmod.restrictions.NoExternalUse;

import java.io.InputStream;
import java.util.Collections;

/**
 * @author <a href="mailto:nicolas.deloof@gmail.com">Nicolas De Loof</a>
 */
@Extension
@Restricted(NoExternalUse.class)
public class CheckConfigurationCommand extends CLICommand {

    @Override
    public String getShortDescription() {
        return "Apply YAML configuration to instance";
    }

    @Override
    protected int run() throws Exception {

        if (!Jenkins.getInstance().hasPermission(Jenkins.ADMINISTER)) {
            return -1;
        }

        ConfigurationAsCode.get().checkWith(new YamlSource<InputStream>(stdin, YamlSource.READ_FROM_INPUTSTREAM));
        return 0;
    }
}
