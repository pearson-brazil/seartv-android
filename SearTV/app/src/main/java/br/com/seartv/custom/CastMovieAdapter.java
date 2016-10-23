package br.com.seartv.custom;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.seartv.R;
import br.com.seartv.model.CastMovieMember;
import br.com.seartv.utils.Utils;

public class CastMovieAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public Context context;
    private final ArrayList<CastMovieMember> castMembers;

    public CastMovieAdapter(Context context, ArrayList<CastMovieMember> castMembers) {
        this.context = context;
        this.castMembers = castMembers;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View castItem = LayoutInflater.from(context).inflate(R.layout.item_cast_character, parent, false);
        return new CastMemberViewHolder(castItem);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final CastMemberViewHolder castMemberViewHolder = (CastMemberViewHolder) holder;

        final CastMovieMember member = castMembers.get(position);

        castMemberViewHolder.name.setText(member.getName());
        castMemberViewHolder.characterName.setText("como " + member.getCharacter());
        castMemberViewHolder.detailsLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Chama o navegador Google Chrome para visualizar a busca
                String url = Utils.getGoogleUrlToSearchPerson(castMembers.get(position).getName());
                try {
                    Intent i = new Intent("android.intent.action.MAIN");
                    i.setComponent(ComponentName.unflattenFromString("com.android.chrome/com.android.chrome.Main"));
                    i.addCategory("android.intent.category.LAUNCHER");
                    i.setData(Uri.parse(url));
                    context.startActivity(i);
                }
                catch(ActivityNotFoundException e) {
                    // Caso o Chrome não esteja instalado
                    Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    context.startActivity(i);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return castMembers.size();
    }

    public class CastMemberViewHolder extends RecyclerView.ViewHolder {

        public ImageView portrait;
        public TextView name;
        public TextView characterName;
        public ProgressBar loading;
        public ImageView detailsLink;

        public CastMemberViewHolder(final View itemView) {
            super(itemView);

            portrait = (ImageView) itemView.findViewById(R.id.portrait);
            name = (TextView) itemView.findViewById(R.id.name);
            characterName = (TextView) itemView.findViewById(R.id.character_name);
            loading = (ProgressBar) itemView.findViewById(R.id.portrait_loading);
            detailsLink = (ImageView) itemView.findViewById(R.id.details_link);
        }
    }
}
