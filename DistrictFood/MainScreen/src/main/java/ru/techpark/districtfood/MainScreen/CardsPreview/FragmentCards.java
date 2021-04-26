package ru.techpark.districtfood.MainScreen.CardsPreview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ru.techpark.districtfood.CallBackListener;
import ru.techpark.districtfood.R;
import ru.techpark.districtfood.MainScreen.Search.Search;

public class FragmentCards extends Fragment implements CallBackListenerTags{

    private RecyclerView recyclerView;
    private CardsViewModel cardsViewModel;
    private TagsViewModel tagsViewModel;
    private CallBackListener callBackListener;
    private CallBackListenerTags callBackListenerTags;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_cards, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (recyclerView == null) {
            recyclerView = getActivity().findViewById(R.id.cards_feed);
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(CardsAdapter.getInstance());

        if (requireContext() instanceof CallBackListener) {
            this.callBackListener = (CallBackListener) requireContext();
        }

        if (requireContext() instanceof CallBackListenerTags) {
            this.callBackListenerTags = (CallBackListenerTags) requireContext();
        }

        Observer<List<Card>> observer = new Observer<List<Card>>() {
            @Override
            public void onChanged(List<Card> cards) {
                if (cards != null) {
                    CardsAdapter.getInstance().setCards(cards, cardsViewModel, callBackListener);
                    Search.getInstance().SetCards(cards);
                }
            }
        };
        cardsViewModel = new ViewModelProvider(getActivity())
                .get(CardsViewModel.class);
        cardsViewModel.refresh();
        cardsViewModel
                .getCards()
                .observe(getViewLifecycleOwner(), observer);

    }

    @Override
    public void onPause() {
        super.onPause();
        recyclerView = null;
    }



    public static FragmentCards sInstance;
    public FragmentCards() {

    }
    public synchronized static FragmentCards getInstance(){
        if (sInstance == null) {
            sInstance = new FragmentCards();
        }
        return sInstance;
    }

    public CardsViewModel GetCardsViewModel() {
        return cardsViewModel;
    }

    @Override
    public void onCallBack(Card card) {
        Observer<List<Tags>> observer = new Observer<List<Tags>>() {
            @Override
            public void onChanged(List<Tags> tags) {
                if (tags != null) {
                    CardsAdapter.getInstance().setTags(tags, tagsViewModel, callBackListenerTags);
                }
            }
        };
        tagsViewModel = new ViewModelProvider(getActivity())
                .get(TagsViewModel.class);
        tagsViewModel.converterTags(card);
        tagsViewModel
                .getTags()
                .observe(getViewLifecycleOwner(), observer);

    }
}
