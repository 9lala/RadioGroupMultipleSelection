package cn.photon.multiple;


import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RadioGroupForMarketLayout extends LinearLayout implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private static final int S_SELECT = 1;
    private static final int M_SELECT = 2;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.flow_rg)
    FlowRadioGroup flowRg;
    private LayoutCheckedistener mListener;

    private String mTitle = "标题";
    private String mContent = "内容1,内容2";

    /**
     * 记录多选信息
     */
    private ArrayList<String> list = new ArrayList<>();


    /**
     * 1 RadioButton 实现单选  2CheckBox 实现多选
     */
    private int radioType = S_SELECT;

    public RadioGroupForMarketLayout(Context context) {
        this(context, null);
    }

    public RadioGroupForMarketLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout layout = (LinearLayout) layoutInflater.inflate(R.layout.layout_radio_group_for_market, null);
        ButterKnife.bind(this, layout);
        addView(layout);

        DisplayMetrics dm = getResources().getDisplayMetrics();
//        flowRg.setLayoutParams(new RadioGroup.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, RadioGroup.LayoutParams.MATCH_PARENT));
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RadioGroupLayout);
        mTitle = a.getString(R.styleable.RadioGroupLayout_layoutTitle);
        mContent = a.getString(R.styleable.RadioGroupLayout_layoutContent);
        radioType = a.getInt(R.styleable.RadioGroupLayout_layoutType, radioType);// 默认为Circle
        a.recycle();
        initView();
        initData();
        initListener();
    }

    private void initData() {
        String[] content = mContent.split(",");
        int padding = DensityUtil.dip2px(getContext(), 12);
        int height = DensityUtil.dip2px(getContext(), 32);
        int width = DensityUtil.dip2px(getContext(), 80);
        int margin = DensityUtil.dip2px(getContext(), 10);
        RadioGroup.LayoutParams lp = new RadioGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        lp.setMargins(0, margin, margin, 0);
        for (int i = 0; i < content.length; i++) {
            if (radioType == M_SELECT) {
                CheckBox rb = new CheckBox(getContext());
                rb.setText(content[i]);
                rb.setPadding(padding, 0, padding, 0);
                rb.setButtonDrawable(null);
                rb.setGravity(Gravity.CENTER);
                rb.setTextSize(13);
                rb.setId(i);
                rb.setBackground(getContext().getResources().getDrawable(R.drawable.radio_selector_market_select));
                rb.setTextColor(getContext().getResources().getColorStateList(R.color.text_color_checkedbox_market_select));
                rb.setHeight(height);
                if (Utils.stringLength(content[i]) < 9) {
                    rb.setWidth(width);
                }
                rb.setOnCheckedChangeListener(this);
                flowRg.addView(rb, lp);
            } else {
                RadioButton rb = new RadioButton(getContext());
                rb.setText(content[i]);
                rb.setPadding(padding, 0, padding, 0);
                rb.setButtonDrawable(null);
                rb.setGravity(Gravity.CENTER);
                rb.setTextSize(13);
                rb.setId(i);
                rb.setBackground(getContext().getResources().getDrawable(R.drawable.radio_selector_market_select));
                rb.setTextColor(getContext().getResources().getColorStateList(R.color.text_color_checkedbox_market_select));
                rb.setHeight(height);
                if (Utils.stringLength(content[i]) < 9) {
                    rb.setWidth(width);
                }
                rb.setOnCheckedChangeListener(this);
                rb.setOnClickListener(this);
                flowRg.addView(rb, lp);
            }


        }
    }

    private void initView() {
        tvTitle.setText(mTitle);
    }


    private void initListener() {
        flowRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (radioType == S_SELECT) {
                    if (mContent != null) {
                        int rbId = checkedId;
                        String[] comtents = mContent.split(",");
                        if (comtents != null && comtents.length >= rbId) {
                            String value = rbId < 0 ? "" : comtents[rbId];
                            if (mListener != null) {
                                mListener.onclick(value);
                            }
                            if (mSelectListener != null) {
                                mSelectListener.onSelect();
                            }
                        }
                    }
                }
            }
        });
    }


    public void setLayoutCheckedListener(LayoutCheckedistener mListener) {
        this.mListener = mListener;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (buttonView instanceof CheckBox) {
            if (isChecked) {
                list.add(((CheckBox) buttonView).getText().toString());
            } else {
                list.remove(((CheckBox) buttonView).getText().toString());
            }
            if (mListener != null) {
                mListener.onclick(Arrays.toString(list.toArray(new String[0])));
            }
            if (mSelectListener != null) {
                mSelectListener.onSelect();
            }
        }
    }

    private boolean hasRadioButtonClicked = false;
    private int checkButtonId;

    /**
     * RadioButton 的点击事件
     *
     * @param v
     */
    @Override
    public void onClick(View v) {
        if (v instanceof RadioButton) {
            if (((RadioButton) v).getParent() instanceof RadioGroup) {
                RadioGroup g = (RadioGroup) ((RadioButton) v).getParent();
                if (v.getId() == g.getCheckedRadioButtonId() && hasRadioButtonClicked && checkButtonId == v.getId()) {
                    g.clearCheck();
                    hasRadioButtonClicked = false;
                } else {
                    checkButtonId = v.getId();
                    hasRadioButtonClicked = true;
                }
            }
        }
    }

    public interface LayoutCheckedistener {
        void onclick(String str);
    }

    public void clearCheck() {
        flowRg.clearCheck();
    }


    /**
     * 筛选页面需要 实时知道结果 没点击一个条件 就需要回调查询接口
     */
    public interface SelectForResult {
        void onSelect();
    }

    public static SelectForResult mSelectListener;

    public static void setSelectForResult(SelectForResult listener) {
        mSelectListener = listener;
    }
}
