package io.bbl.chatview;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.FrameLayout;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.appinventor.components.annotations.DesignerComponent;
import com.google.appinventor.components.runtime.AndroidNonvisibleComponent;
import com.google.appinventor.components.runtime.ComponentContainer;
import com.google.appinventor.components.annotations.SimpleFunction;
import com.google.appinventor.components.runtime.HVArrangement;
import com.google.appinventor.components.annotations.SimpleProperty;

@DesignerComponent(
    version = 1, 
    versionName = "1", 
    description = "ChatView list using recyclerview.", 
    iconName = "icon.png"
)
public class ChatView extends AndroidNonvisibleComponent {
    private final Context context;
    private RecyclerView recyclerView;
    private ChatAdapter adapter;
    private final ArrayList<ChatMessage> messages = new ArrayList<>();

    // Customizable properties
    private int bubbleColorSender = 0xFF42A5F5;
    private int bubbleColorReceiver = 0xFF757575;
    private int textColor = 0xFFFFFFFF;
    private float textSize = 16f;
    private int bubblePadding = 24;
    private int maxBubbleWidth = 900;
    private int bubbleCornerRadius = 20;
    private boolean fontBold = false;
    private boolean fontItalic = false;

    public ChatView(ComponentContainer container) {
        super(container.$form());
        this.context = container.$context();
    }

    @SimpleFunction(description = "Creates the chat view inside the given arrangement.")
    public void CreateChatView(HVArrangement arrangement) {
        recyclerView = new RecyclerView(context);
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        adapter = new ChatAdapter();
        recyclerView.setAdapter(adapter);

        ViewGroup parent = (ViewGroup) arrangement.getView();
        parent.addView(recyclerView);
    }

    @SimpleFunction(description = "Adds a message to the chat. If isSender is true, message is right-aligned.")
    public void AddMessage(String message, boolean isSender) {
        if (recyclerView == null)
            return;
        messages.add(new ChatMessage(message, isSender));
        adapter.notifyItemInserted(messages.size() - 1);
        recyclerView.scrollToPosition(messages.size() - 1);
    }

    @SimpleFunction(description = "Clears all chat messages.")
    public void ClearMessages() {
        messages.clear();
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    // Safe Properties
    @SimpleProperty(description = "Set sender bubble color.")
    public void BubbleColorSender(int color) {
        bubbleColorSender = color;
    }

    @SimpleProperty(description = "Set receiver bubble color.")
    public void BubbleColorReceiver(int color) {
        bubbleColorReceiver = color;
    }

    @SimpleProperty(description = "Set text color.")
    public void TextColor(int color) {
        textColor = color;
    }

    @SimpleProperty(description = "Set text size.")
    public void TextSize(float size) {
        textSize = size;
    }

    @SimpleProperty(description = "Set bubble padding.")
    public void BubblePadding(int padding) {
        bubblePadding = padding;
    }

    @SimpleProperty(description = "Set max bubble width.")
    public void MaxBubbleWidth(int width) {
        maxBubbleWidth = width;
    }

    @SimpleProperty(description = "Set bubble corner radius.")
    public void BubbleCornerRadius(int radius) {
        bubbleCornerRadius = radius;
    }

    @SimpleProperty(description = "Set whether text is bold.")
    public void FontBold(boolean bold) {
        fontBold = bold;
    }

    @SimpleProperty(description = "Set whether text is italic.")
    public void FontItalic(boolean italic) {
        fontItalic = italic;
    }

    // Message model
    class ChatMessage {
        String text;
        boolean isSender;

        ChatMessage(String text, boolean isSender) {
            this.text = text;
            this.isSender = isSender;
        }
    }

    // Adapter
    class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

        @Override
        public ChatViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            FrameLayout container = new FrameLayout(context);
            container.setLayoutParams(new RecyclerView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            container.setPadding(12, 8, 12, 8);

            TextView messageView = new TextView(context);
            messageView.setLayoutParams(new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            container.addView(messageView);

            return new ChatViewHolder(container, messageView);
        }

        @Override
        public void onBindViewHolder(ChatViewHolder holder, int position) {
            ChatMessage msg = messages.get(position);
            holder.textView.setText(msg.text);

            FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) holder.textView.getLayoutParams();
            params.gravity = msg.isSender ? Gravity.END : Gravity.START;
            holder.textView.setLayoutParams(params);

            holder.textView.setPadding(bubblePadding, bubblePadding, bubblePadding, bubblePadding);
            holder.textView.setTextColor(textColor);
            holder.textView.setTextSize(textSize);
            holder.textView.setMaxWidth(maxBubbleWidth);

            int style = Typeface.NORMAL;
            if (fontBold && fontItalic)
                style = Typeface.BOLD_ITALIC;
            else if (fontBold)
                style = Typeface.BOLD;
            else if (fontItalic)
                style = Typeface.ITALIC;
            holder.textView.setTypeface(null, style);

            GradientDrawable drawable = new GradientDrawable();
            drawable.setCornerRadius(bubbleCornerRadius);
            drawable.setColor(msg.isSender ? bubbleColorSender : bubbleColorReceiver);
            holder.textView.setBackground(drawable);
        }

        @Override
        public int getItemCount() {
            return messages.size();
        }

        class ChatViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            ChatViewHolder(View itemView, TextView textView) {
                super(itemView);
                this.textView = textView;
            }
        }
    }
}
