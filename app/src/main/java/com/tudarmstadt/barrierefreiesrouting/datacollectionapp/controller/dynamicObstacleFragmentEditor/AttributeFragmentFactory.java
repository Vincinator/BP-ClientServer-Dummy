package com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.R;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.model.ObstacleDataSingleton;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.attributeEditFragments.CheckBoxAttributeFragment;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.attributeEditFragments.DropdownAttributeFragment;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.attributeEditFragments.NumberAttributeFragment;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.attributeEditFragments.TextAttributeFragment;

import java.util.List;
import java.util.Map;

/**
 * This "Factory" creates a List of Fragments according to the concrete implementation of the
 * abstract Obstacle class.
 * <p>
 * All Fields marked with "@EditableAttribute" Annotation in the BP-Common Project are getting an
 * InputFragment. Via this Input Fragment Obstacle Data can be edited.
 * <p>
 * Between the Fragment and the POJO Obstacle is an ObstacleViewModel. This ViewModel holds the mapping
 * between the Fields and the edited Attributes.
 * <p>
 * After the editing is done, the obstacle is updated with the content from the ViewModel.
 */
public class AttributeFragmentFactory {

    /**
     * Inserts Attribute Fragments into the given fragment. All Attributes are listed in the
     * obstacleViewModel.
     * <p>
     * Depending on the typeParameterClass of the entry, a Number- Text- or CheckBox-
     * AttributeFragment will ne inserted in the fragment container.
     *
     * @param parentFragment the fragment container, where to insert the attributes
     */
    public static void insertAttributeEditFragments(Fragment parentFragment) {

        ClearAllChildFragments(parentFragment);

        Map<String, ObstacleAttribute<?>> obstacleAttributeMap = ObstacleDataSingleton.getInstance().getmObstacleViewModel().attributesMap;
        FragmentManager fragMan = parentFragment.getChildFragmentManager();

        FragmentTransaction fragTransaction = fragMan.beginTransaction();
        for (Map.Entry<String, ObstacleAttribute<?>> entry : obstacleAttributeMap.entrySet()) {
            if (entry.getValue().typeParameterClass == Double.TYPE) {
                fragTransaction.add(R.id.editor_attribute_list_container, NumberAttributeFragment.newInstance(entry.getKey()), entry.getKey());

            } else if (entry.getValue().typeParameterClass == Integer.TYPE) {
                fragTransaction.add(R.id.editor_attribute_list_container, NumberAttributeFragment.newInstance(entry.getKey()), entry.getKey());

            } else if (entry.getValue().typeParameterClass == String.class) {

                // Insert Dropdown Fragment for Attribute
                // only if in BP-Common the EditableAttribute Annotation has marked this attribued as Dropdown.
                if(entry.getValue().isDrowpdown){
                    fragTransaction.add(R.id.editor_attribute_list_container, DropdownAttributeFragment.newInstance(entry.getKey(), entry.getValue().validOptions, entry.getValue().name), entry.getKey());
                }else{
                    // Freetext Editing for string attributes is default.
                    fragTransaction.add(R.id.editor_attribute_list_container, TextAttributeFragment.newInstance(entry.getKey()), entry.getKey());
                }

            } else if (entry.getValue().typeParameterClass == Boolean.TYPE) {
                fragTransaction.add(R.id.editor_attribute_list_container, CheckBoxAttributeFragment.newInstance(entry.getKey()), entry.getKey());

            }
        }
        fragTransaction.commit();

    }


    public static void ClearAllChildFragments(Fragment parentFragment) {
        FragmentManager fragMan = parentFragment.getChildFragmentManager();
        List<Fragment> allCurrentFragments = fragMan.getFragments();
        FragmentTransaction fragTransaction = fragMan.beginTransaction();

        if (allCurrentFragments == null)
            return;

        for (Fragment frag : allCurrentFragments) {
            fragTransaction.remove(frag);
        }
        fragTransaction.commit();
    }


}
