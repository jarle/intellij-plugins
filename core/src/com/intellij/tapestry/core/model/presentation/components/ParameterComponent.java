package com.intellij.tapestry.core.model.presentation.components;

import com.intellij.tapestry.core.TapestryProject;
import com.intellij.tapestry.core.exceptions.NotTapestryElementException;
import com.intellij.tapestry.core.java.IJavaClassType;
import com.intellij.tapestry.core.model.presentation.Component;
import com.intellij.tapestry.core.model.presentation.TapestryParameter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The built-in Parameter element.
 */
public class ParameterComponent extends Component {

    private static Map<String, TapestryParameter> _parameters;

    protected ParameterComponent(IJavaClassType componentClass, TapestryProject project) throws NotTapestryElementException {
        super(componentClass, project);
    }

    /**
     * Returns an instance of the Parameter component.
     *
     * @param tapestryProject the current Tapestry project.
     * @return an instance of the Parameter component.
     */
    public synchronized static ParameterComponent getInstance(TapestryProject tapestryProject) {
        if (_parameters == null) {
            _parameters = new HashMap<String, TapestryParameter>();

            _parameters.put("id", new DummyTapestryParameter(tapestryProject, "name", true));
        }

      @NotNull
      final IJavaClassType classType =
          tapestryProject.getJavaTypeFinder().findType("org.apache.tapestry5.internal.parser.ParameterToken", true);
      return new ParameterComponent(classType, tapestryProject);
    }

    /**
     * {@inheritDoc}
     */
    public String getName() {
        return "parameter";
    }

    /**
     * {@inheritDoc}
     */
    public Map<String, TapestryParameter> getParameters() {
        return _parameters;
    }

    /**
     * {@inheritDoc}
     */
    protected String getElementNameFromClass(String rootPackage) throws NotTapestryElementException {
        return "parameter";
    }
}
