package com.tudarmstadt.barrierefreiesrouting.datacollectionapp.ui.fragments.stepperFragments;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.UiThread;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.stepstone.stepper.BlockingStep;
import com.stepstone.stepper.Step;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.R;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.ObstacleViewModel;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.interfaces.IObstacleViewModelProvider;
import com.tudarmstadt.barrierefreiesrouting.datacollectionapp.model.ObstacleDataSingleton;

import bp.common.model.Obstacle;
import bp.common.model.Stairs;

import static com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.AttributeFragmentFactory.ClearAllChildFragments;
import static com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.AttributeFragmentFactory.insertAttributeEditFragments;
import static com.tudarmstadt.barrierefreiesrouting.datacollectionapp.controller.dynamicObstacleFragmentEditor.ObstacleToViewConverter.convertObstacleToAttributeMap;

/**
 * This Fragment holds all Attributes that can be edited
 */
public class AttributesEditorFragment extends Fragment implements BlockingStep {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.stepfragment_attributes_edit, container, false);


        return v;
    }
    @Override
    public void onSaveInstanceState(final Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    public VerificationError verifyStep() {

        return null;
    }

    @Override
    public void onSelected() {
        ClearAllChildFragments(this);
        // this.obstacleViewModel must be initialized first.
        insertAttributeEditFragments(this);
    }

    @Override
    public void onError(@NonNull VerificationError verificationError) {

    }



    @Override
    @UiThread
    public void onNextClicked(final StepperLayout.OnNextClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.goToNextStep();
            }
        }, 0L);
    }

    @Override
    @UiThread
    public void onCompleteClicked(final StepperLayout.OnCompleteClickedCallback callback) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.complete();
            }
        }, 0L);
    }

    @Override
    @UiThread
    public void onBackClicked(StepperLayout.OnBackClickedCallback callback) {
        Toast.makeText(this.getContext(), "Your custom back action. Here you should cancel currently running operations", Toast.LENGTH_SHORT).show();
        callback.goToPrevStep();
    }
}
