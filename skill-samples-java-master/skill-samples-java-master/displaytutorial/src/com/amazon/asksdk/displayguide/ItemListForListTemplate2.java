/*
 *Copyright 2016-2017 Amazon.com, Inc. or its affiliates. All Rights Reserved.
 *
 *Licensed under the Amazon Software License (the "License"). You may not use
 * this file except in compliance with the License. A copy of the License is located at
 *
 *http://aws.amazon.com/asl/
 *
 * or in the "license" file accompanying this file. This file is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, express or
 * implied. See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.amazon.asksdk.displayguide;

import com.amazon.speech.speechlet.interfaces.display.element.Image;
import com.amazon.speech.speechlet.interfaces.display.element.TextField;
import com.amazon.speech.speechlet.interfaces.display.element.TripleTextContent;
import com.amazon.speech.speechlet.interfaces.display.template.ListTemplate2;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemListForListTemplate1 is a concrete implementation of the ItemList interface. It performs all operations for
 * constructing ListTemplate1 item lists.
 */
public class ItemListForListTemplate2 implements ItemList {

    private List<ListTemplate2.ListItem> items = new ArrayList<>();

    /**
     * Constructs a ListTemplate2.ListItem object with the three arguments of this function and adds it to the ItemList
     * @param image an Image object containing a list of ImageInstance sources and an optional content description
     * @param token a String token associated with the list item
     * @param textContent a ListTemplate2.ListItem.TextContent object - use the {@link #makeTextContent(TextField, TextField, TextField)},
     * {@link #makeTextContent(TextField, TextField)}, or {@link #makeTextContent(TextField, TextField, TextField)}
     *                  method to construct this argument
     */
    @Override
    public void addItem(Image image, String token, TripleTextContent textContent) {
        ListTemplate2.ListItem item = new ListTemplate2.ListItem();
        item.setTextContent((ListTemplate2.ListItem.TextContent)textContent);
        item.setToken(token);
        item.setImage(image);

        items.add(item);
    }

    @Override
    public ListTemplate2.ListItem.TextContent makeTextContent(TextField primary) {
        ListTemplate2.ListItem.TextContent textContent = new ListTemplate2.ListItem.TextContent();
        textContent.setPrimaryText(primary);
        return textContent;
    }

    @Override
    public ListTemplate2.ListItem.TextContent makeTextContent(TextField primary, TextField secondary) {
        ListTemplate2.ListItem.TextContent textContent = makeTextContent(primary);
        textContent.setSecondaryText(secondary);
        return textContent;
    }

    @Override
    public ListTemplate2.ListItem.TextContent makeTextContent(TextField primary, TextField secondary, TextField tertiary) {
        ListTemplate2.ListItem.TextContent textContent = makeTextContent(primary, secondary);
        textContent.setTertiaryText(tertiary);
        return textContent;
    }

    List<ListTemplate2.ListItem> getItems() {
        return items;
    }
}
