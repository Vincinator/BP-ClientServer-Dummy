package com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.stepperFragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.stepstone.stepper.Step;
import com.stepstone.stepper.VerificationError;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.R;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.ObstacleViewModel;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.interfaces.IObstacleProvider;

import bp.common.model.Obstacle;

import static com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.AttributeFragmentFactory.insertAttributeFragments;
import static com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.ObstacleToViewConverter.convertObstacleToAttributeMap;

/**
 * This Fragment holds all Attributes that can be edited
 */
public class AttributesEditorFragment extends Fragment implements Step {

    private ObstacleViewModel obstacleViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.stepfragment_attributes_edit, container, false);

        final Obstacle obstacleToEdit = ((IObstacleProvider) getActivity()).getObstacle();

        obstacleViewModel = new ObstacleViewModel(convertObstacleToAttributeMap(obstacleToEdit, getActivity()), obstacleToEdit);

        insertAttributeFragments(this, obstacleViewModel);


        return v;
    }


    @Override
    public VerificationError verifyStep() {
        return null;
    }

    @Override
    public void onSelected() {

    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }
}