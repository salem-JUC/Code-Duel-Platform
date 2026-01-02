require.config({
  paths: { vs: "/monaco/vs" }
});


require(["vs/editor/editor.main"], function () {
  const editorContainer = document.getElementById("codeEditor");
  
  window.editor = monaco.editor.create(editorContainer, {
      theme: "vs-dark",
      automaticLayout: true,
      suggest: {
        showMethods: true,
        showFunctions: true,
        showConstructors: true,
        showFields: true,
        showVariables: true,
        showClasses: true,
        shareSuggestSelections: true, // Remembers your last choice
        snippetsPreventQuickSuggestions: false // Allows snippets to show up immediately
    },
    quickSuggestions: {
        other: true,
        comments: false,
        strings: false
    }
  });

  // Handle zoom and resize events
  let resizeTimeout;
  function handleResize() {
    clearTimeout(resizeTimeout);
    resizeTimeout = setTimeout(() => {
      if (window.editor) {
        window.editor.layout();
      }
    }, 100);
  }

  // Listen for window resize and zoom
  window.addEventListener('resize', handleResize);
  
  // Use ResizeObserver for more accurate container size tracking
  if (window.ResizeObserver) {
    const resizeObserver = new ResizeObserver(handleResize);
    resizeObserver.observe(editorContainer);
  }

  window.dispatchEvent(new Event('editorReady'));
});


function defineLanguage(programmingLanguage) {
    const model = window.editor.getModel();
    let starterCode;
    if(programmingLanguage == "Java"){
        monaco.editor.setModelLanguage(model , "java");
starterCode = `
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    }
}
`;
        window.editor.setValue(starterCode);
    }else if(programmingLanguage == "Python"){
        monaco.editor.setModelLanguage(model, "python");
    }
    
    
}

function resetEditor() {
    if(window.editor.getModel().getLanguageId() == "java"){
        window.editor.setValue(`
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
    }
}
`
        );
    }else if(window.editor.getModel().getLanguageId() == "python"){
        window.editor.setValue(" ");
    }
}

