package ro.sync.demo;

import ro.sync.ecss.extensions.api.AuthorDocumentFilter;
import ro.sync.ecss.extensions.api.AuthorDocumentFilterBypass;

public class FunkyDocFilter extends AuthorDocumentFilter {
  @Override
  public void insertText(
      AuthorDocumentFilterBypass filterBypass,
      int offset,
      String toInsert) {
    if ("a".equals(toInsert)) {
      super.insertText(filterBypass, offset, "fraieereee");
    } else {
      super.insertText(filterBypass, offset, toInsert);
    }
  }
}
