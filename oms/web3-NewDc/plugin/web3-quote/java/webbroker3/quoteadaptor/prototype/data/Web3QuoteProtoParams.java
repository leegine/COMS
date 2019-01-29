head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.42.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	Web3QuoteProtoParams.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.quoteadaptor.prototype.data;

import com.fitechlabs.dbind.Params;
import com.fitechlabs.dbind.PrimaryKey;
import com.fitechlabs.dbind.RowType;

/** 
 * web3_quote_proto�e�[�u���̃f�[�^�x�[�X���R�[�h�̒l�ύX�ɗ��p����p�����[�^���쐬���郆�[�e�B���e�B�N���X�ł��B 
 * <p> 
 * �ʏ�A�R���X�g���N�^�̂�����1�𗘗p���Ċ�����{@@link Web3QuoteProtoRow}�I�u�W�F�N�g����p�����[�^���쐬���܂��B 
 * �����āAsetter���\�b�h�𗘗p���Ċ�]����t�B�[���h�̒l��ύX���܂��B 
 * �����āA{@@link Web3QuoteProtoParams}�I�u�W�F�N�g�������Ƃ��ēn���A�V�������R�[�h���쐬�܂��͑��݂��郌�R�[�h���X�V���܂��B 
 * <p> 
 * {@@link Web3QuoteProtoParams}��{@@link Web3QuoteProtoRow}�C���^�t�F�[�X����������̂ŁA���̃N���X�ł͊e�t�B�[���h�ɑ΂�getter�����setter���\�b�h��񋟂��Ă��܂��B 
 * <p> 
 * @@author xTrade�R�[�h�W�F�l���[�^ 
 * 
 * @@see com.fitechlabs.dbind.Row 
 * @@see com.fitechlabs.dbind.PrimaryKey 
 * @@see Web3QuoteProtoPK 
 * @@see Web3QuoteProtoRow 
 */
public class Web3QuoteProtoParams extends Params implements Web3QuoteProtoRow {


  /** �g�b�v���x�����b�Z�[�W�̏ꍇ�ɗ��p�����^�O�� */
  public static final String TAGNAME = "row";


  /** ����params�Ƌ�ʂ��邽�߃��b�Z�[�W���ŗ��p�����|�������t�B�b�N�^�C�v */
  public static final String PTYPE = "web3_quote_proto";


  /** �N�G�����ŗ��p�����ꍇ��row������킷RowType */
  public static final RowType TYPE = Web3QuoteProtoRow.TYPE;


  /** 
   * Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType�I�u�W�F�N�g��Ԃ��܂��B 
   * @@return ����Params�I�u�W�F�N�g�ɕR�t���e�[�u����RowType 
   */
  public RowType getRowType() {
    return Web3QuoteProtoRow.TYPE;
  }


  /** 
   * <em>quote_data_id</em>�J�����̒l 
   */
  public  long  quote_data_id;

  /** 
   * <em>quote_date</em>�J�����̒l 
   */
  public  String  quote_date;

  /** 
   * <em>real_type</em>�J�����̒l 
   */
  public  webbroker3.quoteadaptor.RealType  real_type;

  /** 
   * <em>data_type</em>�J�����̒l 
   */
  public  webbroker3.quoteadaptor.DataType  data_type;

  /** 
   * <em>market_code</em>�J�����̒l 
   */
  public  String  market_code;

  /** 
   * <em>product_code</em>�J�����̒l 
   */
  public  String  product_code;

  /** 
   * <em>contract_month</em>�J�����̒l 
   */
  public  String  contract_month;

  /** 
   * <em>put_and_call</em>�J�����̒l 
   */
  public  String  put_and_call;

  /** 
   * <em>strike_price</em>�J�����̒l 
   */
  public  Double  strike_price;

  /** 
   * <em>open_price</em>�J�����̒l 
   */
  public  Double  open_price;

  /** 
   * <em>open_price_time</em>�J�����̒l 
   */
  public  String  open_price_time;

  /** 
   * <em>high_price</em>�J�����̒l 
   */
  public  Double  high_price;

  /** 
   * <em>high_price_time</em>�J�����̒l 
   */
  public  String  high_price_time;

  /** 
   * <em>low_price</em>�J�����̒l 
   */
  public  Double  low_price;

  /** 
   * <em>low_price_time</em>�J�����̒l 
   */
  public  String  low_price_time;

  /** 
   * <em>current_price</em>�J�����̒l 
   */
  public  Double  current_price;

  /** 
   * <em>current_price_time</em>�J�����̒l 
   */
  public  String  current_price_time;

  /** 
   * <em>current_price_flag</em>�J�����̒l 
   */
  public  webbroker3.quoteadaptor.CurrentPriceFlag  current_price_flag;

  /** 
   * <em>change</em>�J�����̒l 
   */
  public  Double  change;

  /** 
   * <em>volume</em>�J�����̒l 
   */
  public  Double  volume;

  /** 
   * <em>volume_time</em>�J�����̒l 
   */
  public  String  volume_time;

  /** 
   * <em>ask_price_title</em>�J�����̒l 
   */
  public  webbroker3.quoteadaptor.AskPriceTitle  ask_price_title;

  /** 
   * <em>ask_price</em>�J�����̒l 
   */
  public  Double  ask_price;

  /** 
   * <em>ask_price_time</em>�J�����̒l 
   */
  public  String  ask_price_time;

  /** 
   * <em>bid_price_title</em>�J�����̒l 
   */
  public  webbroker3.quoteadaptor.BidPriceTitle  bid_price_title;

  /** 
   * <em>bid_price</em>�J�����̒l 
   */
  public  Double  bid_price;

  /** 
   * <em>bid_price_time</em>�J�����̒l 
   */
  public  String  bid_price_time;

  /** 
   * <em>base_price</em>�J�����̒l 
   */
  public  Double  base_price;

  /** 
   * <em>created_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  created_timestamp;

  /** 
   * <em>last_updated_timestamp</em>�J�����̒l 
   */
  public  java.sql.Timestamp  last_updated_timestamp;

  private boolean quote_data_id_is_set = false;
  private boolean quote_data_id_is_modified = false;
  private boolean quote_date_is_set = false;
  private boolean quote_date_is_modified = false;
  private boolean real_type_is_set = false;
  private boolean real_type_is_modified = false;
  private boolean data_type_is_set = false;
  private boolean data_type_is_modified = false;
  private boolean market_code_is_set = false;
  private boolean market_code_is_modified = false;
  private boolean product_code_is_set = false;
  private boolean product_code_is_modified = false;
  private boolean contract_month_is_set = false;
  private boolean contract_month_is_modified = false;
  private boolean put_and_call_is_set = false;
  private boolean put_and_call_is_modified = false;
  private boolean strike_price_is_set = false;
  private boolean strike_price_is_modified = false;
  private boolean open_price_is_set = false;
  private boolean open_price_is_modified = false;
  private boolean open_price_time_is_set = false;
  private boolean open_price_time_is_modified = false;
  private boolean high_price_is_set = false;
  private boolean high_price_is_modified = false;
  private boolean high_price_time_is_set = false;
  private boolean high_price_time_is_modified = false;
  private boolean low_price_is_set = false;
  private boolean low_price_is_modified = false;
  private boolean low_price_time_is_set = false;
  private boolean low_price_time_is_modified = false;
  private boolean current_price_is_set = false;
  private boolean current_price_is_modified = false;
  private boolean current_price_time_is_set = false;
  private boolean current_price_time_is_modified = false;
  private boolean current_price_flag_is_set = false;
  private boolean current_price_flag_is_modified = false;
  private boolean change_is_set = false;
  private boolean change_is_modified = false;
  private boolean volume_is_set = false;
  private boolean volume_is_modified = false;
  private boolean volume_time_is_set = false;
  private boolean volume_time_is_modified = false;
  private boolean ask_price_title_is_set = false;
  private boolean ask_price_title_is_modified = false;
  private boolean ask_price_is_set = false;
  private boolean ask_price_is_modified = false;
  private boolean ask_price_time_is_set = false;
  private boolean ask_price_time_is_modified = false;
  private boolean bid_price_title_is_set = false;
  private boolean bid_price_title_is_modified = false;
  private boolean bid_price_is_set = false;
  private boolean bid_price_is_modified = false;
  private boolean bid_price_time_is_set = false;
  private boolean bid_price_time_is_modified = false;
  private boolean base_price_is_set = false;
  private boolean base_price_is_modified = false;
  private boolean created_timestamp_is_set = false;
  private boolean created_timestamp_is_modified = false;
  private boolean last_updated_timestamp_is_set = false;
  private boolean last_updated_timestamp_is_modified = false;


  /** 
   * ���̃I�u�W�F�N�g���J���}��؂�̕�����\���ŕԂ��܂��B 
   */
  public String toString() {
    return "{"
       + "quote_data_id=" + quote_data_id
      + "," + "quote_date=" +quote_date
      + "," + "real_type=" +real_type
      + "," + "data_type=" +data_type
      + "," + "market_code=" +market_code
      + "," + "product_code=" +product_code
      + "," + "contract_month=" +contract_month
      + "," + "put_and_call=" +put_and_call
      + "," + "strike_price=" +strike_price
      + "," + "open_price=" +open_price
      + "," + "open_price_time=" +open_price_time
      + "," + "high_price=" +high_price
      + "," + "high_price_time=" +high_price_time
      + "," + "low_price=" +low_price
      + "," + "low_price_time=" +low_price_time
      + "," + "current_price=" +current_price
      + "," + "current_price_time=" +current_price_time
      + "," + "current_price_flag=" +current_price_flag
      + "," + "change=" +change
      + "," + "volume=" +volume
      + "," + "volume_time=" +volume_time
      + "," + "ask_price_title=" +ask_price_title
      + "," + "ask_price=" +ask_price
      + "," + "ask_price_time=" +ask_price_time
      + "," + "bid_price_title=" +bid_price_title
      + "," + "bid_price=" +bid_price
      + "," + "bid_price_time=" +bid_price_time
      + "," + "base_price=" +base_price
      + "," + "created_timestamp=" +created_timestamp
      + "," + "last_updated_timestamp=" +last_updated_timestamp
      + "}";
  }


  /** 
   * �l�����ݒ��Web3QuoteProtoParams�I�u�W�F�N�g���쐬���܂��B 
   */
  public Web3QuoteProtoParams() {
    quote_data_id_is_modified = true;
  }


  /** 
   * �w��̃T�u�N���X�����N�̒l�ŃI�u�W�F�N�g���쐬���܂��B 
   * @@param tableName �T�u�N���X�����N�ŗ��p����T�u�N���X�e�[�u���� 
   */


  /** 
   * �w���Web3QuoteProtoRow�I�u�W�F�N�g�̒l�𗘗p����Web3QuoteProtoParams�I�u�W�F�N�g���쐬���܂��B 
   * @@param p_row �e���v���[�g�Ƃ��ė��p����Web3QuoteProtoRow�I�u�W�F�N�g 
   */
  public Web3QuoteProtoParams( Web3QuoteProtoRow p_row )
  {
    quote_data_id = p_row.getQuoteDataId();
    quote_data_id_is_set = p_row.getQuoteDataIdIsSet();
    quote_data_id_is_modified = p_row.getQuoteDataIdIsModified();
    quote_date = p_row.getQuoteDate();
    quote_date_is_set = p_row.getQuoteDateIsSet();
    quote_date_is_modified = p_row.getQuoteDateIsModified();
    real_type = p_row.getRealType();
    real_type_is_set = p_row.getRealTypeIsSet();
    real_type_is_modified = p_row.getRealTypeIsModified();
    data_type = p_row.getDataType();
    data_type_is_set = p_row.getDataTypeIsSet();
    data_type_is_modified = p_row.getDataTypeIsModified();
    market_code = p_row.getMarketCode();
    market_code_is_set = p_row.getMarketCodeIsSet();
    market_code_is_modified = p_row.getMarketCodeIsModified();
    product_code = p_row.getProductCode();
    product_code_is_set = p_row.getProductCodeIsSet();
    product_code_is_modified = p_row.getProductCodeIsModified();
    contract_month = p_row.getContractMonth();
    contract_month_is_set = p_row.getContractMonthIsSet();
    contract_month_is_modified = p_row.getContractMonthIsModified();
    put_and_call = p_row.getPutAndCall();
    put_and_call_is_set = p_row.getPutAndCallIsSet();
    put_and_call_is_modified = p_row.getPutAndCallIsModified();
    if ( !p_row.getStrikePriceIsNull() )
      strike_price = new Double( p_row.getStrikePrice() );
    strike_price_is_set = p_row.getStrikePriceIsSet();
    strike_price_is_modified = p_row.getStrikePriceIsModified();
    if ( !p_row.getOpenPriceIsNull() )
      open_price = new Double( p_row.getOpenPrice() );
    open_price_is_set = p_row.getOpenPriceIsSet();
    open_price_is_modified = p_row.getOpenPriceIsModified();
    open_price_time = p_row.getOpenPriceTime();
    open_price_time_is_set = p_row.getOpenPriceTimeIsSet();
    open_price_time_is_modified = p_row.getOpenPriceTimeIsModified();
    if ( !p_row.getHighPriceIsNull() )
      high_price = new Double( p_row.getHighPrice() );
    high_price_is_set = p_row.getHighPriceIsSet();
    high_price_is_modified = p_row.getHighPriceIsModified();
    high_price_time = p_row.getHighPriceTime();
    high_price_time_is_set = p_row.getHighPriceTimeIsSet();
    high_price_time_is_modified = p_row.getHighPriceTimeIsModified();
    if ( !p_row.getLowPriceIsNull() )
      low_price = new Double( p_row.getLowPrice() );
    low_price_is_set = p_row.getLowPriceIsSet();
    low_price_is_modified = p_row.getLowPriceIsModified();
    low_price_time = p_row.getLowPriceTime();
    low_price_time_is_set = p_row.getLowPriceTimeIsSet();
    low_price_time_is_modified = p_row.getLowPriceTimeIsModified();
    if ( !p_row.getCurrentPriceIsNull() )
      current_price = new Double( p_row.getCurrentPrice() );
    current_price_is_set = p_row.getCurrentPriceIsSet();
    current_price_is_modified = p_row.getCurrentPriceIsModified();
    current_price_time = p_row.getCurrentPriceTime();
    current_price_time_is_set = p_row.getCurrentPriceTimeIsSet();
    current_price_time_is_modified = p_row.getCurrentPriceTimeIsModified();
    current_price_flag = p_row.getCurrentPriceFlag();
    current_price_flag_is_set = p_row.getCurrentPriceFlagIsSet();
    current_price_flag_is_modified = p_row.getCurrentPriceFlagIsModified();
    if ( !p_row.getChangeIsNull() )
      change = new Double( p_row.getChange() );
    change_is_set = p_row.getChangeIsSet();
    change_is_modified = p_row.getChangeIsModified();
    if ( !p_row.getVolumeIsNull() )
      volume = new Double( p_row.getVolume() );
    volume_is_set = p_row.getVolumeIsSet();
    volume_is_modified = p_row.getVolumeIsModified();
    volume_time = p_row.getVolumeTime();
    volume_time_is_set = p_row.getVolumeTimeIsSet();
    volume_time_is_modified = p_row.getVolumeTimeIsModified();
    ask_price_title = p_row.getAskPriceTitle();
    ask_price_title_is_set = p_row.getAskPriceTitleIsSet();
    ask_price_title_is_modified = p_row.getAskPriceTitleIsModified();
    if ( !p_row.getAskPriceIsNull() )
      ask_price = new Double( p_row.getAskPrice() );
    ask_price_is_set = p_row.getAskPriceIsSet();
    ask_price_is_modified = p_row.getAskPriceIsModified();
    ask_price_time = p_row.getAskPriceTime();
    ask_price_time_is_set = p_row.getAskPriceTimeIsSet();
    ask_price_time_is_modified = p_row.getAskPriceTimeIsModified();
    bid_price_title = p_row.getBidPriceTitle();
    bid_price_title_is_set = p_row.getBidPriceTitleIsSet();
    bid_price_title_is_modified = p_row.getBidPriceTitleIsModified();
    if ( !p_row.getBidPriceIsNull() )
      bid_price = new Double( p_row.getBidPrice() );
    bid_price_is_set = p_row.getBidPriceIsSet();
    bid_price_is_modified = p_row.getBidPriceIsModified();
    bid_price_time = p_row.getBidPriceTime();
    bid_price_time_is_set = p_row.getBidPriceTimeIsSet();
    bid_price_time_is_modified = p_row.getBidPriceTimeIsModified();
    if ( !p_row.getBasePriceIsNull() )
      base_price = new Double( p_row.getBasePrice() );
    base_price_is_set = p_row.getBasePriceIsSet();
    base_price_is_modified = p_row.getBasePriceIsModified();
    created_timestamp = p_row.getCreatedTimestamp();
    created_timestamp_is_set = p_row.getCreatedTimestampIsSet();
    created_timestamp_is_modified = p_row.getCreatedTimestampIsModified();
    last_updated_timestamp = p_row.getLastUpdatedTimestamp();
    last_updated_timestamp_is_set = p_row.getLastUpdatedTimestampIsSet();
    last_updated_timestamp_is_modified = p_row.getLastUpdatedTimestampIsModified();
  }


  /** 
   *@@see com.fitechlabs.dbind.Params#markAllValuesAsSet() 
   */
    public void markAllValuesAsSet() {
      super.markAllValuesAsSet();
      quote_date_is_set = true;
      quote_date_is_modified = true;
      real_type_is_set = true;
      real_type_is_modified = true;
      data_type_is_set = true;
      data_type_is_modified = true;
      market_code_is_set = true;
      market_code_is_modified = true;
      product_code_is_set = true;
      product_code_is_modified = true;
      contract_month_is_set = true;
      contract_month_is_modified = true;
      put_and_call_is_set = true;
      put_and_call_is_modified = true;
      strike_price_is_set = true;
      strike_price_is_modified = true;
      open_price_is_set = true;
      open_price_is_modified = true;
      open_price_time_is_set = true;
      open_price_time_is_modified = true;
      high_price_is_set = true;
      high_price_is_modified = true;
      high_price_time_is_set = true;
      high_price_time_is_modified = true;
      low_price_is_set = true;
      low_price_is_modified = true;
      low_price_time_is_set = true;
      low_price_time_is_modified = true;
      current_price_is_set = true;
      current_price_is_modified = true;
      current_price_time_is_set = true;
      current_price_time_is_modified = true;
      current_price_flag_is_set = true;
      current_price_flag_is_modified = true;
      change_is_set = true;
      change_is_modified = true;
      volume_is_set = true;
      volume_is_modified = true;
      volume_time_is_set = true;
      volume_time_is_modified = true;
      ask_price_title_is_set = true;
      ask_price_title_is_modified = true;
      ask_price_is_set = true;
      ask_price_is_modified = true;
      ask_price_time_is_set = true;
      ask_price_time_is_modified = true;
      bid_price_title_is_set = true;
      bid_price_title_is_modified = true;
      bid_price_is_set = true;
      bid_price_is_modified = true;
      bid_price_time_is_set = true;
      bid_price_time_is_modified = true;
      base_price_is_set = true;
      base_price_is_modified = true;
      created_timestamp_is_set = true;
      created_timestamp_is_modified = true;
      last_updated_timestamp_is_set = true;
      last_updated_timestamp_is_modified = true;
    }


  /** 
   * ��r�ΏƂ̃I�u�W�F�N�g�Ɠ��ꂩ�ǂ������ׂ܂��B 
   * @@param other ��r�ΏƂ̃I�u�W�F�N�g 
   */
  public boolean equals( Object other )
  {
    if ( !( other instanceof Web3QuoteProtoRow ) )
       return false;
    return fieldsEqual( (Web3QuoteProtoRow) other );
  }


  /** 
   * �w���Row�I�u�W�F�N�g�ƃt�B�[���h�l�����ׂĈ�v���Ă��邩�ǂ������ׂ܂��B 
   * @@param row ��r�ΏƂ�Web3QuoteProtoRow 
   * @@return ���ׂẴt�B�[���h�l����v�̏ꍇtrue�A�����łȂ����false 
   */
  public boolean fieldsEqual( Web3QuoteProtoRow row )
  {
    if ( quote_data_id != row.getQuoteDataId() )
      return false;
    if ( quote_date == null ) {
      if ( row.getQuoteDate() != null )
        return false;
    } else if ( !quote_date.equals( row.getQuoteDate() ) ) {
        return false;
    }
    if ( real_type == null ) {
      if ( row.getRealType() != null )
        return false;
    } else if ( !real_type.equals( row.getRealType() ) ) {
        return false;
    }
    if ( data_type == null ) {
      if ( row.getDataType() != null )
        return false;
    } else if ( !data_type.equals( row.getDataType() ) ) {
        return false;
    }
    if ( market_code == null ) {
      if ( row.getMarketCode() != null )
        return false;
    } else if ( !market_code.equals( row.getMarketCode() ) ) {
        return false;
    }
    if ( product_code == null ) {
      if ( row.getProductCode() != null )
        return false;
    } else if ( !product_code.equals( row.getProductCode() ) ) {
        return false;
    }
    if ( contract_month == null ) {
      if ( row.getContractMonth() != null )
        return false;
    } else if ( !contract_month.equals( row.getContractMonth() ) ) {
        return false;
    }
    if ( put_and_call == null ) {
      if ( row.getPutAndCall() != null )
        return false;
    } else if ( !put_and_call.equals( row.getPutAndCall() ) ) {
        return false;
    }
    if ( strike_price == null ) {
      if ( !row.getStrikePriceIsNull() )
        return false;
    } else if ( row.getStrikePriceIsNull() || ( strike_price.doubleValue() != row.getStrikePrice() ) ) {
        return false;
    }
    if ( open_price == null ) {
      if ( !row.getOpenPriceIsNull() )
        return false;
    } else if ( row.getOpenPriceIsNull() || ( open_price.doubleValue() != row.getOpenPrice() ) ) {
        return false;
    }
    if ( open_price_time == null ) {
      if ( row.getOpenPriceTime() != null )
        return false;
    } else if ( !open_price_time.equals( row.getOpenPriceTime() ) ) {
        return false;
    }
    if ( high_price == null ) {
      if ( !row.getHighPriceIsNull() )
        return false;
    } else if ( row.getHighPriceIsNull() || ( high_price.doubleValue() != row.getHighPrice() ) ) {
        return false;
    }
    if ( high_price_time == null ) {
      if ( row.getHighPriceTime() != null )
        return false;
    } else if ( !high_price_time.equals( row.getHighPriceTime() ) ) {
        return false;
    }
    if ( low_price == null ) {
      if ( !row.getLowPriceIsNull() )
        return false;
    } else if ( row.getLowPriceIsNull() || ( low_price.doubleValue() != row.getLowPrice() ) ) {
        return false;
    }
    if ( low_price_time == null ) {
      if ( row.getLowPriceTime() != null )
        return false;
    } else if ( !low_price_time.equals( row.getLowPriceTime() ) ) {
        return false;
    }
    if ( current_price == null ) {
      if ( !row.getCurrentPriceIsNull() )
        return false;
    } else if ( row.getCurrentPriceIsNull() || ( current_price.doubleValue() != row.getCurrentPrice() ) ) {
        return false;
    }
    if ( current_price_time == null ) {
      if ( row.getCurrentPriceTime() != null )
        return false;
    } else if ( !current_price_time.equals( row.getCurrentPriceTime() ) ) {
        return false;
    }
    if ( current_price_flag == null ) {
      if ( row.getCurrentPriceFlag() != null )
        return false;
    } else if ( !current_price_flag.equals( row.getCurrentPriceFlag() ) ) {
        return false;
    }
    if ( change == null ) {
      if ( !row.getChangeIsNull() )
        return false;
    } else if ( row.getChangeIsNull() || ( change.doubleValue() != row.getChange() ) ) {
        return false;
    }
    if ( volume == null ) {
      if ( !row.getVolumeIsNull() )
        return false;
    } else if ( row.getVolumeIsNull() || ( volume.doubleValue() != row.getVolume() ) ) {
        return false;
    }
    if ( volume_time == null ) {
      if ( row.getVolumeTime() != null )
        return false;
    } else if ( !volume_time.equals( row.getVolumeTime() ) ) {
        return false;
    }
    if ( ask_price_title == null ) {
      if ( row.getAskPriceTitle() != null )
        return false;
    } else if ( !ask_price_title.equals( row.getAskPriceTitle() ) ) {
        return false;
    }
    if ( ask_price == null ) {
      if ( !row.getAskPriceIsNull() )
        return false;
    } else if ( row.getAskPriceIsNull() || ( ask_price.doubleValue() != row.getAskPrice() ) ) {
        return false;
    }
    if ( ask_price_time == null ) {
      if ( row.getAskPriceTime() != null )
        return false;
    } else if ( !ask_price_time.equals( row.getAskPriceTime() ) ) {
        return false;
    }
    if ( bid_price_title == null ) {
      if ( row.getBidPriceTitle() != null )
        return false;
    } else if ( !bid_price_title.equals( row.getBidPriceTitle() ) ) {
        return false;
    }
    if ( bid_price == null ) {
      if ( !row.getBidPriceIsNull() )
        return false;
    } else if ( row.getBidPriceIsNull() || ( bid_price.doubleValue() != row.getBidPrice() ) ) {
        return false;
    }
    if ( bid_price_time == null ) {
      if ( row.getBidPriceTime() != null )
        return false;
    } else if ( !bid_price_time.equals( row.getBidPriceTime() ) ) {
        return false;
    }
    if ( base_price == null ) {
      if ( !row.getBasePriceIsNull() )
        return false;
    } else if ( row.getBasePriceIsNull() || ( base_price.doubleValue() != row.getBasePrice() ) ) {
        return false;
    }
    if ( created_timestamp == null ) {
      if ( row.getCreatedTimestamp() != null )
        return false;
    } else if ( !created_timestamp.equals( row.getCreatedTimestamp() ) ) {
        return false;
    }
    if ( last_updated_timestamp == null ) {
      if ( row.getLastUpdatedTimestamp() != null )
        return false;
    } else if ( !last_updated_timestamp.equals( row.getLastUpdatedTimestamp() ) ) {
        return false;
    }
    return true;
  }


  /** 
   * ���̃I�u�W�F�N�g�̃n�b�V���R�[�h��Ԃ��܂��B 
   * @@return int�B���ɎZ�o���ۑ����ꂽ���̂�Ԃ��ꍇ���� 
   */
  public int hashCode() {
      return  ((int) quote_data_id)
        + (quote_date!=null? quote_date.hashCode(): 0) 
        + (real_type!=null? real_type.hashCode(): 0) 
        + (data_type!=null? data_type.hashCode(): 0) 
        + (market_code!=null? market_code.hashCode(): 0) 
        + (product_code!=null? product_code.hashCode(): 0) 
        + (contract_month!=null? contract_month.hashCode(): 0) 
        + (put_and_call!=null? put_and_call.hashCode(): 0) 
        + (strike_price!=null? strike_price.hashCode(): 0) 
        + (open_price!=null? open_price.hashCode(): 0) 
        + (open_price_time!=null? open_price_time.hashCode(): 0) 
        + (high_price!=null? high_price.hashCode(): 0) 
        + (high_price_time!=null? high_price_time.hashCode(): 0) 
        + (low_price!=null? low_price.hashCode(): 0) 
        + (low_price_time!=null? low_price_time.hashCode(): 0) 
        + (current_price!=null? current_price.hashCode(): 0) 
        + (current_price_time!=null? current_price_time.hashCode(): 0) 
        + (current_price_flag!=null? current_price_flag.hashCode(): 0) 
        + (change!=null? change.hashCode(): 0) 
        + (volume!=null? volume.hashCode(): 0) 
        + (volume_time!=null? volume_time.hashCode(): 0) 
        + (ask_price_title!=null? ask_price_title.hashCode(): 0) 
        + (ask_price!=null? ask_price.hashCode(): 0) 
        + (ask_price_time!=null? ask_price_time.hashCode(): 0) 
        + (bid_price_title!=null? bid_price_title.hashCode(): 0) 
        + (bid_price!=null? bid_price.hashCode(): 0) 
        + (bid_price_time!=null? bid_price_time.hashCode(): 0) 
        + (base_price!=null? base_price.hashCode(): 0) 
        + (created_timestamp!=null? created_timestamp.hashCode(): 0) 
        + (last_updated_timestamp!=null? last_updated_timestamp.hashCode(): 0) 
      ;
  }


  /** 
   * @@see com.fitechlabs.dbind.Params#assertValidForInsert() 
   */
	protected void assertValidForInsert() throws IllegalArgumentException {
		super.assertValidForInsert();
		if ( !quote_date_is_set )
			throw new IllegalArgumentException("non-nullable field 'quote_date' must be set before inserting.");
		if ( !market_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'market_code' must be set before inserting.");
		if ( !product_code_is_set )
			throw new IllegalArgumentException("non-nullable field 'product_code' must be set before inserting.");
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toInsertMap() 
   */
	public java.util.Map toInsertMap() {
		assertValidForInsert();
		java.util.Map map = super.toInsertMap();
		map.put("quote_data_id",new Long(quote_data_id));
		map.put("quote_date",quote_date);
		if ( real_type_is_set )
			map.put("real_type",real_type);
		if ( data_type_is_set )
			map.put("data_type",data_type);
		map.put("market_code",market_code);
		map.put("product_code",product_code);
		if ( contract_month != null )
			map.put("contract_month",contract_month);
		if ( put_and_call != null )
			map.put("put_and_call",put_and_call);
		if ( strike_price != null )
			map.put("strike_price",strike_price);
		if ( open_price != null )
			map.put("open_price",open_price);
		if ( open_price_time != null )
			map.put("open_price_time",open_price_time);
		if ( high_price != null )
			map.put("high_price",high_price);
		if ( high_price_time != null )
			map.put("high_price_time",high_price_time);
		if ( low_price != null )
			map.put("low_price",low_price);
		if ( low_price_time != null )
			map.put("low_price_time",low_price_time);
		if ( current_price != null )
			map.put("current_price",current_price);
		if ( current_price_time != null )
			map.put("current_price_time",current_price_time);
		if ( current_price_flag_is_set )
			map.put("current_price_flag",current_price_flag);
		if ( change != null )
			map.put("change",change);
		if ( volume != null )
			map.put("volume",volume);
		if ( volume_time != null )
			map.put("volume_time",volume_time);
		if ( ask_price_title_is_set )
			map.put("ask_price_title",ask_price_title);
		if ( ask_price != null )
			map.put("ask_price",ask_price);
		if ( ask_price_time != null )
			map.put("ask_price_time",ask_price_time);
		if ( bid_price_title_is_set )
			map.put("bid_price_title",bid_price_title);
		if ( bid_price != null )
			map.put("bid_price",bid_price);
		if ( bid_price_time != null )
			map.put("bid_price_time",bid_price_time);
		if ( base_price != null )
			map.put("base_price",base_price);
		if ( created_timestamp_is_set )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_set )
			map.put("last_updated_timestamp",last_updated_timestamp);
		return map;
	}


  /** 
   * @@see com.fitechlabs.dbind.Row#toUpdateMap() 
   */
	public java.util.Map toUpdateMap() {
		java.util.Map map = super.toUpdateMap();
		if ( quote_date_is_modified )
			map.put("quote_date",quote_date);
		if ( real_type_is_modified )
			map.put("real_type",real_type);
		if ( data_type_is_modified )
			map.put("data_type",data_type);
		if ( market_code_is_modified )
			map.put("market_code",market_code);
		if ( product_code_is_modified )
			map.put("product_code",product_code);
		if ( contract_month_is_modified )
			map.put("contract_month",contract_month);
		if ( put_and_call_is_modified )
			map.put("put_and_call",put_and_call);
		if ( strike_price_is_modified )
			map.put("strike_price",strike_price);
		if ( open_price_is_modified )
			map.put("open_price",open_price);
		if ( open_price_time_is_modified )
			map.put("open_price_time",open_price_time);
		if ( high_price_is_modified )
			map.put("high_price",high_price);
		if ( high_price_time_is_modified )
			map.put("high_price_time",high_price_time);
		if ( low_price_is_modified )
			map.put("low_price",low_price);
		if ( low_price_time_is_modified )
			map.put("low_price_time",low_price_time);
		if ( current_price_is_modified )
			map.put("current_price",current_price);
		if ( current_price_time_is_modified )
			map.put("current_price_time",current_price_time);
		if ( current_price_flag_is_modified )
			map.put("current_price_flag",current_price_flag);
		if ( change_is_modified )
			map.put("change",change);
		if ( volume_is_modified )
			map.put("volume",volume);
		if ( volume_time_is_modified )
			map.put("volume_time",volume_time);
		if ( ask_price_title_is_modified )
			map.put("ask_price_title",ask_price_title);
		if ( ask_price_is_modified )
			map.put("ask_price",ask_price);
		if ( ask_price_time_is_modified )
			map.put("ask_price_time",ask_price_time);
		if ( bid_price_title_is_modified )
			map.put("bid_price_title",bid_price_title);
		if ( bid_price_is_modified )
			map.put("bid_price",bid_price);
		if ( bid_price_time_is_modified )
			map.put("bid_price_time",bid_price_time);
		if ( base_price_is_modified )
			map.put("base_price",base_price);
		if ( created_timestamp_is_modified )
			map.put("created_timestamp",created_timestamp);
		if ( last_updated_timestamp_is_modified )
			map.put("last_updated_timestamp",last_updated_timestamp);
		if (map.size() == 0) {
			if ( quote_date_is_set )
				map.put("quote_date",quote_date);
			if ( real_type_is_set )
				map.put("real_type",real_type);
			if ( data_type_is_set )
				map.put("data_type",data_type);
			if ( market_code_is_set )
				map.put("market_code",market_code);
			if ( product_code_is_set )
				map.put("product_code",product_code);
			map.put("contract_month",contract_month);
			map.put("put_and_call",put_and_call);
			map.put("strike_price",strike_price);
			map.put("open_price",open_price);
			map.put("open_price_time",open_price_time);
			map.put("high_price",high_price);
			map.put("high_price_time",high_price_time);
			map.put("low_price",low_price);
			map.put("low_price_time",low_price_time);
			map.put("current_price",current_price);
			map.put("current_price_time",current_price_time);
			if ( current_price_flag_is_set )
				map.put("current_price_flag",current_price_flag);
			map.put("change",change);
			map.put("volume",volume);
			map.put("volume_time",volume_time);
			if ( ask_price_title_is_set )
				map.put("ask_price_title",ask_price_title);
			map.put("ask_price",ask_price);
			map.put("ask_price_time",ask_price_time);
			if ( bid_price_title_is_set )
				map.put("bid_price_title",bid_price_title);
			map.put("bid_price",bid_price);
			map.put("bid_price_time",bid_price_time);
			map.put("base_price",base_price);
			if ( created_timestamp_is_set )
				map.put("created_timestamp",created_timestamp);
			if ( last_updated_timestamp_is_set )
				map.put("last_updated_timestamp",last_updated_timestamp);
		}
		return map;
	}


  /** 
   * <em>quote_data_id</em>�J�����̒l���擾���܂��B
   * @@return long�̒l 
   */
  public final long getQuoteDataId()
  {
    return quote_data_id;
  }


  /** 
   * <em>quote_data_id</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuoteDataIdIsSet() {
    return quote_data_id_is_set;
  }


  /** 
   * <em>quote_data_id</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuoteDataIdIsModified() {
    return quote_data_id_is_modified;
  }


  /** 
   * <em>quote_date</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getQuoteDate()
  {
    return quote_date;
  }


  /** 
   * <em>quote_date</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuoteDateIsSet() {
    return quote_date_is_set;
  }


  /** 
   * <em>quote_date</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getQuoteDateIsModified() {
    return quote_date_is_modified;
  }


  /** 
   * <em>real_type</em>�J�����̒l���擾���܂��B
   * @@return webbroker3.quoteadaptor.RealType�̒l 
   */
  public final webbroker3.quoteadaptor.RealType getRealType()
  {
    return real_type;
  }


  /** 
   * <em>real_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRealTypeIsSet() {
    return real_type_is_set;
  }


  /** 
   * <em>real_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getRealTypeIsModified() {
    return real_type_is_modified;
  }


  /** 
   * <em>data_type</em>�J�����̒l���擾���܂��B
   * @@return webbroker3.quoteadaptor.DataType�̒l 
   */
  public final webbroker3.quoteadaptor.DataType getDataType()
  {
    return data_type;
  }


  /** 
   * <em>data_type</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDataTypeIsSet() {
    return data_type_is_set;
  }


  /** 
   * <em>data_type</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getDataTypeIsModified() {
    return data_type_is_modified;
  }


  /** 
   * <em>market_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getMarketCode()
  {
    return market_code;
  }


  /** 
   * <em>market_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsSet() {
    return market_code_is_set;
  }


  /** 
   * <em>market_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getMarketCodeIsModified() {
    return market_code_is_modified;
  }


  /** 
   * <em>product_code</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getProductCode()
  {
    return product_code;
  }


  /** 
   * <em>product_code</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsSet() {
    return product_code_is_set;
  }


  /** 
   * <em>product_code</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getProductCodeIsModified() {
    return product_code_is_modified;
  }


  /** 
   * <em>contract_month</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getContractMonth()
  {
    return contract_month;
  }


  /** 
   * <em>contract_month</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractMonthIsSet() {
    return contract_month_is_set;
  }


  /** 
   * <em>contract_month</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getContractMonthIsModified() {
    return contract_month_is_modified;
  }


  /** 
   * <em>put_and_call</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getPutAndCall()
  {
    return put_and_call;
  }


  /** 
   * <em>put_and_call</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPutAndCallIsSet() {
    return put_and_call_is_set;
  }


  /** 
   * <em>put_and_call</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getPutAndCallIsModified() {
    return put_and_call_is_modified;
  }


  /** 
   * <em>strike_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getStrikePrice()
  {
    return ( strike_price==null? ((double)0): strike_price.doubleValue() );
  }


  /** 
   * <em>strike_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getStrikePriceIsNull()
  {
    return strike_price == null;
  }


  /** 
   * <em>strike_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStrikePriceIsSet() {
    return strike_price_is_set;
  }


  /** 
   * <em>strike_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getStrikePriceIsModified() {
    return strike_price_is_modified;
  }


  /** 
   * <em>open_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getOpenPrice()
  {
    return ( open_price==null? ((double)0): open_price.doubleValue() );
  }


  /** 
   * <em>open_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getOpenPriceIsNull()
  {
    return open_price == null;
  }


  /** 
   * <em>open_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenPriceIsSet() {
    return open_price_is_set;
  }


  /** 
   * <em>open_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenPriceIsModified() {
    return open_price_is_modified;
  }


  /** 
   * <em>open_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getOpenPriceTime()
  {
    return open_price_time;
  }


  /** 
   * <em>open_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenPriceTimeIsSet() {
    return open_price_time_is_set;
  }


  /** 
   * <em>open_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getOpenPriceTimeIsModified() {
    return open_price_time_is_modified;
  }


  /** 
   * <em>high_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getHighPrice()
  {
    return ( high_price==null? ((double)0): high_price.doubleValue() );
  }


  /** 
   * <em>high_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getHighPriceIsNull()
  {
    return high_price == null;
  }


  /** 
   * <em>high_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighPriceIsSet() {
    return high_price_is_set;
  }


  /** 
   * <em>high_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighPriceIsModified() {
    return high_price_is_modified;
  }


  /** 
   * <em>high_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getHighPriceTime()
  {
    return high_price_time;
  }


  /** 
   * <em>high_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighPriceTimeIsSet() {
    return high_price_time_is_set;
  }


  /** 
   * <em>high_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getHighPriceTimeIsModified() {
    return high_price_time_is_modified;
  }


  /** 
   * <em>low_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getLowPrice()
  {
    return ( low_price==null? ((double)0): low_price.doubleValue() );
  }


  /** 
   * <em>low_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getLowPriceIsNull()
  {
    return low_price == null;
  }


  /** 
   * <em>low_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowPriceIsSet() {
    return low_price_is_set;
  }


  /** 
   * <em>low_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowPriceIsModified() {
    return low_price_is_modified;
  }


  /** 
   * <em>low_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getLowPriceTime()
  {
    return low_price_time;
  }


  /** 
   * <em>low_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowPriceTimeIsSet() {
    return low_price_time_is_set;
  }


  /** 
   * <em>low_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLowPriceTimeIsModified() {
    return low_price_time_is_modified;
  }


  /** 
   * <em>current_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getCurrentPrice()
  {
    return ( current_price==null? ((double)0): current_price.doubleValue() );
  }


  /** 
   * <em>current_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getCurrentPriceIsNull()
  {
    return current_price == null;
  }


  /** 
   * <em>current_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceIsSet() {
    return current_price_is_set;
  }


  /** 
   * <em>current_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceIsModified() {
    return current_price_is_modified;
  }


  /** 
   * <em>current_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getCurrentPriceTime()
  {
    return current_price_time;
  }


  /** 
   * <em>current_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceTimeIsSet() {
    return current_price_time_is_set;
  }


  /** 
   * <em>current_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceTimeIsModified() {
    return current_price_time_is_modified;
  }


  /** 
   * <em>current_price_flag</em>�J�����̒l���擾���܂��B
   * @@return webbroker3.quoteadaptor.CurrentPriceFlag�̒l 
   */
  public final webbroker3.quoteadaptor.CurrentPriceFlag getCurrentPriceFlag()
  {
    return current_price_flag;
  }


  /** 
   * <em>current_price_flag</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceFlagIsSet() {
    return current_price_flag_is_set;
  }


  /** 
   * <em>current_price_flag</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCurrentPriceFlagIsModified() {
    return current_price_flag_is_modified;
  }


  /** 
   * <em>change</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getChange()
  {
    return ( change==null? ((double)0): change.doubleValue() );
  }


  /** 
   * <em>change</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getChangeIsNull()
  {
    return change == null;
  }


  /** 
   * <em>change</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeIsSet() {
    return change_is_set;
  }


  /** 
   * <em>change</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getChangeIsModified() {
    return change_is_modified;
  }


  /** 
   * <em>volume</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getVolume()
  {
    return ( volume==null? ((double)0): volume.doubleValue() );
  }


  /** 
   * <em>volume</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getVolumeIsNull()
  {
    return volume == null;
  }


  /** 
   * <em>volume</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getVolumeIsSet() {
    return volume_is_set;
  }


  /** 
   * <em>volume</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getVolumeIsModified() {
    return volume_is_modified;
  }


  /** 
   * <em>volume_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getVolumeTime()
  {
    return volume_time;
  }


  /** 
   * <em>volume_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getVolumeTimeIsSet() {
    return volume_time_is_set;
  }


  /** 
   * <em>volume_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getVolumeTimeIsModified() {
    return volume_time_is_modified;
  }


  /** 
   * <em>ask_price_title</em>�J�����̒l���擾���܂��B
   * @@return webbroker3.quoteadaptor.AskPriceTitle�̒l 
   */
  public final webbroker3.quoteadaptor.AskPriceTitle getAskPriceTitle()
  {
    return ask_price_title;
  }


  /** 
   * <em>ask_price_title</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceTitleIsSet() {
    return ask_price_title_is_set;
  }


  /** 
   * <em>ask_price_title</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceTitleIsModified() {
    return ask_price_title_is_modified;
  }


  /** 
   * <em>ask_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getAskPrice()
  {
    return ( ask_price==null? ((double)0): ask_price.doubleValue() );
  }


  /** 
   * <em>ask_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getAskPriceIsNull()
  {
    return ask_price == null;
  }


  /** 
   * <em>ask_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceIsSet() {
    return ask_price_is_set;
  }


  /** 
   * <em>ask_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceIsModified() {
    return ask_price_is_modified;
  }


  /** 
   * <em>ask_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getAskPriceTime()
  {
    return ask_price_time;
  }


  /** 
   * <em>ask_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceTimeIsSet() {
    return ask_price_time_is_set;
  }


  /** 
   * <em>ask_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getAskPriceTimeIsModified() {
    return ask_price_time_is_modified;
  }


  /** 
   * <em>bid_price_title</em>�J�����̒l���擾���܂��B
   * @@return webbroker3.quoteadaptor.BidPriceTitle�̒l 
   */
  public final webbroker3.quoteadaptor.BidPriceTitle getBidPriceTitle()
  {
    return bid_price_title;
  }


  /** 
   * <em>bid_price_title</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceTitleIsSet() {
    return bid_price_title_is_set;
  }


  /** 
   * <em>bid_price_title</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceTitleIsModified() {
    return bid_price_title_is_modified;
  }


  /** 
   * <em>bid_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBidPrice()
  {
    return ( bid_price==null? ((double)0): bid_price.doubleValue() );
  }


  /** 
   * <em>bid_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBidPriceIsNull()
  {
    return bid_price == null;
  }


  /** 
   * <em>bid_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceIsSet() {
    return bid_price_is_set;
  }


  /** 
   * <em>bid_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceIsModified() {
    return bid_price_is_modified;
  }


  /** 
   * <em>bid_price_time</em>�J�����̒l���擾���܂��B
   * @@return String�̒l 
   */
  public final String getBidPriceTime()
  {
    return bid_price_time;
  }


  /** 
   * <em>bid_price_time</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceTimeIsSet() {
    return bid_price_time_is_set;
  }


  /** 
   * <em>bid_price_time</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBidPriceTimeIsModified() {
    return bid_price_time_is_modified;
  }


  /** 
   * <em>base_price</em>�J�����̒l���擾���܂��B
   * @@return double�̒l 
   */
  public final double getBasePrice()
  {
    return ( base_price==null? ((double)0): base_price.doubleValue() );
  }


  /** 
   * <em>base_price</em>�J�����̒l��null���ǂ������ׂ܂��B
   * @@return �Ώۂ̃f�[�^�x�[�X�J�����̒l��null�̏ꍇ��true 
   */
  public final boolean getBasePriceIsNull()
  {
    return base_price == null;
  }


  /** 
   * <em>base_price</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBasePriceIsSet() {
    return base_price_is_set;
  }


  /** 
   * <em>base_price</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getBasePriceIsModified() {
    return base_price_is_modified;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getCreatedTimestamp()
  {
    return created_timestamp;
  }


  /** 
   * <em>created_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsSet() {
    return created_timestamp_is_set;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getCreatedTimestampIsModified() {
    return created_timestamp_is_modified;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���擾���܂��B
   * @@return java.sql.Timestamp�̒l 
   */
  public final java.sql.Timestamp getLastUpdatedTimestamp()
  {
    return last_updated_timestamp;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����ɒl���ݒ肳��Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɐݒ肳��Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsSet() {
    return last_updated_timestamp_is_set;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l���ύX����Ă��邩�ǂ������ׂ܂��B
   * @@return �l�����ɕύX����Ă���ꍇ��true�A�����łȂ����false 
   */
  public final boolean getLastUpdatedTimestampIsModified() {
    return last_updated_timestamp_is_modified;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getPrimaryKey() 
   */
  public PrimaryKey getPrimaryKey()
  {
    return new Web3QuoteProtoPK(quote_data_id);
  }


  /** 
   * <em>quote_data_id</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quoteDataId <em>quote_data_id</em>�J�����̒l������킷18���ȉ���long�l 
   */
  public final void setQuoteDataId( long p_quoteDataId )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quote_data_id = p_quoteDataId;
    quote_data_id_is_set = true;
    quote_data_id_is_modified = true;
  }


  /** 
   * <em>quote_date</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_quoteDate <em>quote_date</em>�J�����̒l������킷8���ȉ���String�l 
   */
  public final void setQuoteDate( String p_quoteDate )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    quote_date = p_quoteDate;
    quote_date_is_set = true;
    quote_date_is_modified = true;
  }


  /** 
   * <em>real_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_realType <em>real_type</em>�J�����̒l������킷1���ȉ���webbroker3.quoteadaptor.RealType�l 
   */
  public final void setRealType( webbroker3.quoteadaptor.RealType p_realType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    real_type = p_realType;
    real_type_is_set = true;
    real_type_is_modified = true;
  }


  /** 
   * <em>data_type</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_dataType <em>data_type</em>�J�����̒l������킷1���ȉ���webbroker3.quoteadaptor.DataType�l 
   */
  public final void setDataType( webbroker3.quoteadaptor.DataType p_dataType )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    data_type = p_dataType;
    data_type_is_set = true;
    data_type_is_modified = true;
  }


  /** 
   * <em>market_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_marketCode <em>market_code</em>�J�����̒l������킷2���ȉ���String�l 
   */
  public final void setMarketCode( String p_marketCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    market_code = p_marketCode;
    market_code_is_set = true;
    market_code_is_modified = true;
  }


  /** 
   * <em>product_code</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_productCode <em>product_code</em>�J�����̒l������킷9���ȉ���String�l 
   */
  public final void setProductCode( String p_productCode )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    product_code = p_productCode;
    product_code_is_set = true;
    product_code_is_modified = true;
  }


  /** 
   * <em>contract_month</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_contractMonth <em>contract_month</em>�J�����̒l������킷6���ȉ���String�l 
   */
  public final void setContractMonth( String p_contractMonth )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    contract_month = p_contractMonth;
    contract_month_is_set = true;
    contract_month_is_modified = true;
  }


  /** 
   * <em>put_and_call</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_putAndCall <em>put_and_call</em>�J�����̒l������킷1���ȉ���String�l 
   */
  public final void setPutAndCall( String p_putAndCall )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    put_and_call = p_putAndCall;
    put_and_call_is_set = true;
    put_and_call_is_modified = true;
  }


  /** 
   * <em>strike_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_strikePrice <em>strike_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setStrikePrice( double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = new Double(p_strikePrice);
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>strike_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setStrikePrice( Double p_strikePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    strike_price = p_strikePrice;
    strike_price_is_set = true;
    strike_price_is_modified = true;
  }


  /** 
   * <em>open_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openPrice <em>open_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setOpenPrice( double p_openPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_price = new Double(p_openPrice);
    open_price_is_set = true;
    open_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>open_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setOpenPrice( Double p_openPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    open_price = p_openPrice;
    open_price_is_set = true;
    open_price_is_modified = true;
  }


  /** 
   * <em>open_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_openPriceTime <em>open_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setOpenPriceTime( String p_openPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    open_price_time = p_openPriceTime;
    open_price_time_is_set = true;
    open_price_time_is_modified = true;
  }


  /** 
   * <em>high_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_highPrice <em>high_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setHighPrice( double p_highPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_price = new Double(p_highPrice);
    high_price_is_set = true;
    high_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>high_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setHighPrice( Double p_highPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    high_price = p_highPrice;
    high_price_is_set = true;
    high_price_is_modified = true;
  }


  /** 
   * <em>high_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_highPriceTime <em>high_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setHighPriceTime( String p_highPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    high_price_time = p_highPriceTime;
    high_price_time_is_set = true;
    high_price_time_is_modified = true;
  }


  /** 
   * <em>low_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lowPrice <em>low_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setLowPrice( double p_lowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_price = new Double(p_lowPrice);
    low_price_is_set = true;
    low_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>low_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setLowPrice( Double p_lowPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    low_price = p_lowPrice;
    low_price_is_set = true;
    low_price_is_modified = true;
  }


  /** 
   * <em>low_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lowPriceTime <em>low_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setLowPriceTime( String p_lowPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    low_price_time = p_lowPriceTime;
    low_price_time_is_set = true;
    low_price_time_is_modified = true;
  }


  /** 
   * <em>current_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currentPrice <em>current_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setCurrentPrice( double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = new Double(p_currentPrice);
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>current_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setCurrentPrice( Double p_currentPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    current_price = p_currentPrice;
    current_price_is_set = true;
    current_price_is_modified = true;
  }


  /** 
   * <em>current_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currentPriceTime <em>current_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setCurrentPriceTime( String p_currentPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price_time = p_currentPriceTime;
    current_price_time_is_set = true;
    current_price_time_is_modified = true;
  }


  /** 
   * <em>current_price_flag</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_currentPriceFlag <em>current_price_flag</em>�J�����̒l������킷1���ȉ���webbroker3.quoteadaptor.CurrentPriceFlag�l 
   */
  public final void setCurrentPriceFlag( webbroker3.quoteadaptor.CurrentPriceFlag p_currentPriceFlag )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    current_price_flag = p_currentPriceFlag;
    current_price_flag_is_set = true;
    current_price_flag_is_modified = true;
  }


  /** 
   * <em>change</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_change <em>change</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setChange( double p_change )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    change = new Double(p_change);
    change_is_set = true;
    change_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>change</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setChange( Double p_change )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    change = p_change;
    change_is_set = true;
    change_is_modified = true;
  }


  /** 
   * <em>volume</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_volume <em>volume</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setVolume( double p_volume )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    volume = new Double(p_volume);
    volume_is_set = true;
    volume_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>volume</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setVolume( Double p_volume )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    volume = p_volume;
    volume_is_set = true;
    volume_is_modified = true;
  }


  /** 
   * <em>volume_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_volumeTime <em>volume_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setVolumeTime( String p_volumeTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    volume_time = p_volumeTime;
    volume_time_is_set = true;
    volume_time_is_modified = true;
  }


  /** 
   * <em>ask_price_title</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_askPriceTitle <em>ask_price_title</em>�J�����̒l������킷1���ȉ���webbroker3.quoteadaptor.AskPriceTitle�l 
   */
  public final void setAskPriceTitle( webbroker3.quoteadaptor.AskPriceTitle p_askPriceTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price_title = p_askPriceTitle;
    ask_price_title_is_set = true;
    ask_price_title_is_modified = true;
  }


  /** 
   * <em>ask_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_askPrice <em>ask_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setAskPrice( double p_askPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price = new Double(p_askPrice);
    ask_price_is_set = true;
    ask_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>ask_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setAskPrice( Double p_askPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price = p_askPrice;
    ask_price_is_set = true;
    ask_price_is_modified = true;
  }


  /** 
   * <em>ask_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_askPriceTime <em>ask_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setAskPriceTime( String p_askPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    ask_price_time = p_askPriceTime;
    ask_price_time_is_set = true;
    ask_price_time_is_modified = true;
  }


  /** 
   * <em>bid_price_title</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bidPriceTitle <em>bid_price_title</em>�J�����̒l������킷1���ȉ���webbroker3.quoteadaptor.BidPriceTitle�l 
   */
  public final void setBidPriceTitle( webbroker3.quoteadaptor.BidPriceTitle p_bidPriceTitle )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price_title = p_bidPriceTitle;
    bid_price_title_is_set = true;
    bid_price_title_is_modified = true;
  }


  /** 
   * <em>bid_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bidPrice <em>bid_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBidPrice( double p_bidPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price = new Double(p_bidPrice);
    bid_price_is_set = true;
    bid_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>bid_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBidPrice( Double p_bidPrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price = p_bidPrice;
    bid_price_is_set = true;
    bid_price_is_modified = true;
  }


  /** 
   * <em>bid_price_time</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_bidPriceTime <em>bid_price_time</em>�J�����̒l������킷4���ȉ���String�l 
   */
  public final void setBidPriceTime( String p_bidPriceTime )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    bid_price_time = p_bidPriceTime;
    bid_price_time_is_set = true;
    bid_price_time_is_modified = true;
  }


  /** 
   * <em>base_price</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_basePrice <em>base_price</em>�J�����̒l������킷18���ȉ���double�l�ŁA���̐��x��6���܂�
   */
  public final void setBasePrice( double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = new Double(p_basePrice);
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * �w��̃I�u�W�F�N�g�i�ꍇ�ɂ���Ă�null�j���g����<em>base_price</em>�J�����ɒl��ݒ肵�܂��B 
   */
  public final void setBasePrice( Double p_basePrice )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    base_price = p_basePrice;
    base_price_is_set = true;
    base_price_is_modified = true;
  }


  /** 
   * <em>created_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_createdTimestamp <em>created_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setCreatedTimestamp( java.sql.Timestamp p_createdTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = p_createdTimestamp;
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>created_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setCreatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    created_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    created_timestamp_is_set = true;
    created_timestamp_is_modified = true;
  }


  /** 
   * <em>last_updated_timestamp</em>�J�����̒l��ݒ肵�܂��B 
   *
   * @@param p_lastUpdatedTimestamp <em>last_updated_timestamp</em>�J�����̒l������킷java.sql.Timestamp�l
   */
  public final void setLastUpdatedTimestamp( java.sql.Timestamp p_lastUpdatedTimestamp )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object cann't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = p_lastUpdatedTimestamp;
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


   /** 
   * ��ʂ�java.util.Date�I�u�W�F�N�g���g����<em>last_updated_timestamp</em>�J�����ɒl��ݒ肵�܂��B 
   * @@param date �ݒ肷��l���܂�java.util.Date 
    */
  public final void setLastUpdatedTimestamp( java.util.Date date )  {
    if(!mutable())
      throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
    last_updated_timestamp = (date==null? null: new java.sql.Timestamp( date.getTime() ));
    last_updated_timestamp_is_set = true;
    last_updated_timestamp_is_modified = true;
  }


  /** 
   * @@see com.fitechlabs.dbind.Row#getColumn(String) 
   */
    public Object getColumn( String name ) {
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("ask_price_title") ) {
                    return this.ask_price_title;
                }
                else if ( name.equals("ask_price") ) {
                    return this.ask_price;
                }
                else if ( name.equals("ask_price_time") ) {
                    return this.ask_price_time;
                }
                break;
            case 'b':
                if ( name.equals("bid_price_title") ) {
                    return this.bid_price_title;
                }
                else if ( name.equals("bid_price") ) {
                    return this.bid_price;
                }
                else if ( name.equals("bid_price_time") ) {
                    return this.bid_price_time;
                }
                else if ( name.equals("base_price") ) {
                    return this.base_price;
                }
                break;
            case 'c':
                if ( name.equals("contract_month") ) {
                    return this.contract_month;
                }
                else if ( name.equals("current_price") ) {
                    return this.current_price;
                }
                else if ( name.equals("current_price_time") ) {
                    return this.current_price_time;
                }
                else if ( name.equals("current_price_flag") ) {
                    return this.current_price_flag;
                }
                else if ( name.equals("change") ) {
                    return this.change;
                }
                else if ( name.equals("created_timestamp") ) {
                    return this.created_timestamp;
                }
                break;
            case 'd':
                if ( name.equals("data_type") ) {
                    return this.data_type;
                }
                break;
            case 'h':
                if ( name.equals("high_price") ) {
                    return this.high_price;
                }
                else if ( name.equals("high_price_time") ) {
                    return this.high_price_time;
                }
                break;
            case 'l':
                if ( name.equals("low_price") ) {
                    return this.low_price;
                }
                else if ( name.equals("low_price_time") ) {
                    return this.low_price_time;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    return this.last_updated_timestamp;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    return this.market_code;
                }
                break;
            case 'o':
                if ( name.equals("open_price") ) {
                    return this.open_price;
                }
                else if ( name.equals("open_price_time") ) {
                    return this.open_price_time;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    return this.product_code;
                }
                else if ( name.equals("put_and_call") ) {
                    return this.put_and_call;
                }
                break;
            case 'q':
                if ( name.equals("quote_data_id") ) {
                    return new Long( quote_data_id );
                }
                else if ( name.equals("quote_date") ) {
                    return this.quote_date;
                }
                break;
            case 'r':
                if ( name.equals("real_type") ) {
                    return this.real_type;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    return this.strike_price;
                }
                break;
            case 'v':
                if ( name.equals("volume") ) {
                    return this.volume;
                }
                else if ( name.equals("volume_time") ) {
                    return this.volume_time;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }


  /** 
   * @@see com.fitechlabs.dbind.Params#setColumn(String, Object) 
   */
    public void setColumn( String name, Object value ) {
        if(!mutable())
            throw new UnsupportedOperationException("This Params object can't be modified. Use copy contructor to create a new Params object for modification.");
        if ( name == null || name.length() <= 0 )
            throw new IllegalArgumentException( "name cannot be null." );
        switch ( name.charAt(0) ) {
            case 'a':
                if ( name.equals("ask_price_title") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.AskPriceTitle) )
                        throw new IllegalArgumentException( "value for 'ask_price_title' must be webbroker3.quoteadaptor.AskPriceTitle: '"+value+"' is not." );
                    this.ask_price_title = (webbroker3.quoteadaptor.AskPriceTitle) value;
                    if (this.ask_price_title_is_set)
                        this.ask_price_title_is_modified = true;
                    this.ask_price_title_is_set = true;
                    return;
                }
                else if ( name.equals("ask_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'ask_price' must be Double: '"+value+"' is not." );
                    this.ask_price = (Double) value;
                    if (this.ask_price_is_set)
                        this.ask_price_is_modified = true;
                    this.ask_price_is_set = true;
                    return;
                }
                else if ( name.equals("ask_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'ask_price_time' must be String: '"+value+"' is not." );
                    this.ask_price_time = (String) value;
                    if (this.ask_price_time_is_set)
                        this.ask_price_time_is_modified = true;
                    this.ask_price_time_is_set = true;
                    return;
                }
                break;
            case 'b':
                if ( name.equals("bid_price_title") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.BidPriceTitle) )
                        throw new IllegalArgumentException( "value for 'bid_price_title' must be webbroker3.quoteadaptor.BidPriceTitle: '"+value+"' is not." );
                    this.bid_price_title = (webbroker3.quoteadaptor.BidPriceTitle) value;
                    if (this.bid_price_title_is_set)
                        this.bid_price_title_is_modified = true;
                    this.bid_price_title_is_set = true;
                    return;
                }
                else if ( name.equals("bid_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'bid_price' must be Double: '"+value+"' is not." );
                    this.bid_price = (Double) value;
                    if (this.bid_price_is_set)
                        this.bid_price_is_modified = true;
                    this.bid_price_is_set = true;
                    return;
                }
                else if ( name.equals("bid_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'bid_price_time' must be String: '"+value+"' is not." );
                    this.bid_price_time = (String) value;
                    if (this.bid_price_time_is_set)
                        this.bid_price_time_is_modified = true;
                    this.bid_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("base_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'base_price' must be Double: '"+value+"' is not." );
                    this.base_price = (Double) value;
                    if (this.base_price_is_set)
                        this.base_price_is_modified = true;
                    this.base_price_is_set = true;
                    return;
                }
                break;
            case 'c':
                if ( name.equals("contract_month") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'contract_month' must be String: '"+value+"' is not." );
                    this.contract_month = (String) value;
                    if (this.contract_month_is_set)
                        this.contract_month_is_modified = true;
                    this.contract_month_is_set = true;
                    return;
                }
                else if ( name.equals("current_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'current_price' must be Double: '"+value+"' is not." );
                    this.current_price = (Double) value;
                    if (this.current_price_is_set)
                        this.current_price_is_modified = true;
                    this.current_price_is_set = true;
                    return;
                }
                else if ( name.equals("current_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'current_price_time' must be String: '"+value+"' is not." );
                    this.current_price_time = (String) value;
                    if (this.current_price_time_is_set)
                        this.current_price_time_is_modified = true;
                    this.current_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("current_price_flag") ) {
                    if ( value != null )
                      if ( !(value instanceof webbroker3.quoteadaptor.CurrentPriceFlag) )
                        throw new IllegalArgumentException( "value for 'current_price_flag' must be webbroker3.quoteadaptor.CurrentPriceFlag: '"+value+"' is not." );
                    this.current_price_flag = (webbroker3.quoteadaptor.CurrentPriceFlag) value;
                    if (this.current_price_flag_is_set)
                        this.current_price_flag_is_modified = true;
                    this.current_price_flag_is_set = true;
                    return;
                }
                else if ( name.equals("change") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'change' must be Double: '"+value+"' is not." );
                    this.change = (Double) value;
                    if (this.change_is_set)
                        this.change_is_modified = true;
                    this.change_is_set = true;
                    return;
                }
                else if ( name.equals("created_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'created_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.created_timestamp = (java.sql.Timestamp) value;
                    if (this.created_timestamp_is_set)
                        this.created_timestamp_is_modified = true;
                    this.created_timestamp_is_set = true;
                    return;
                }
                break;
            case 'd':
                if ( name.equals("data_type") ) {
                    if ( !(value instanceof webbroker3.quoteadaptor.DataType) )
                        throw new IllegalArgumentException( "value for 'data_type' must be webbroker3.quoteadaptor.DataType: '"+value+"' is not." );
                    this.data_type = (webbroker3.quoteadaptor.DataType) value;
                    if (this.data_type_is_set)
                        this.data_type_is_modified = true;
                    this.data_type_is_set = true;
                    return;
                }
                break;
            case 'h':
                if ( name.equals("high_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'high_price' must be Double: '"+value+"' is not." );
                    this.high_price = (Double) value;
                    if (this.high_price_is_set)
                        this.high_price_is_modified = true;
                    this.high_price_is_set = true;
                    return;
                }
                else if ( name.equals("high_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'high_price_time' must be String: '"+value+"' is not." );
                    this.high_price_time = (String) value;
                    if (this.high_price_time_is_set)
                        this.high_price_time_is_modified = true;
                    this.high_price_time_is_set = true;
                    return;
                }
                break;
            case 'l':
                if ( name.equals("low_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'low_price' must be Double: '"+value+"' is not." );
                    this.low_price = (Double) value;
                    if (this.low_price_is_set)
                        this.low_price_is_modified = true;
                    this.low_price_is_set = true;
                    return;
                }
                else if ( name.equals("low_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'low_price_time' must be String: '"+value+"' is not." );
                    this.low_price_time = (String) value;
                    if (this.low_price_time_is_set)
                        this.low_price_time_is_modified = true;
                    this.low_price_time_is_set = true;
                    return;
                }
                else if ( name.equals("last_updated_timestamp") ) {
                    if ( !(value instanceof java.sql.Timestamp) )
                        throw new IllegalArgumentException( "value for 'last_updated_timestamp' must be java.sql.Timestamp: '"+value+"' is not." );
                    this.last_updated_timestamp = (java.sql.Timestamp) value;
                    if (this.last_updated_timestamp_is_set)
                        this.last_updated_timestamp_is_modified = true;
                    this.last_updated_timestamp_is_set = true;
                    return;
                }
                break;
            case 'm':
                if ( name.equals("market_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'market_code' must be String: '"+value+"' is not." );
                    this.market_code = (String) value;
                    if (this.market_code_is_set)
                        this.market_code_is_modified = true;
                    this.market_code_is_set = true;
                    return;
                }
                break;
            case 'o':
                if ( name.equals("open_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'open_price' must be Double: '"+value+"' is not." );
                    this.open_price = (Double) value;
                    if (this.open_price_is_set)
                        this.open_price_is_modified = true;
                    this.open_price_is_set = true;
                    return;
                }
                else if ( name.equals("open_price_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'open_price_time' must be String: '"+value+"' is not." );
                    this.open_price_time = (String) value;
                    if (this.open_price_time_is_set)
                        this.open_price_time_is_modified = true;
                    this.open_price_time_is_set = true;
                    return;
                }
                break;
            case 'p':
                if ( name.equals("product_code") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'product_code' must be String: '"+value+"' is not." );
                    this.product_code = (String) value;
                    if (this.product_code_is_set)
                        this.product_code_is_modified = true;
                    this.product_code_is_set = true;
                    return;
                }
                else if ( name.equals("put_and_call") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'put_and_call' must be String: '"+value+"' is not." );
                    this.put_and_call = (String) value;
                    if (this.put_and_call_is_set)
                        this.put_and_call_is_modified = true;
                    this.put_and_call_is_set = true;
                    return;
                }
                break;
            case 'q':
                if ( name.equals("quote_data_id") ) {
                    if ( !(value instanceof Long) )
                        throw new IllegalArgumentException( "value for 'quote_data_id' must be Long: '"+value+"' is not." );
                    this.quote_data_id = ((Long) value).longValue();
                    if (this.quote_data_id_is_set)
                        this.quote_data_id_is_modified = true;
                    this.quote_data_id_is_set = true;
                    return;
                }
                else if ( name.equals("quote_date") ) {
                    if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'quote_date' must be String: '"+value+"' is not." );
                    this.quote_date = (String) value;
                    if (this.quote_date_is_set)
                        this.quote_date_is_modified = true;
                    this.quote_date_is_set = true;
                    return;
                }
                break;
            case 'r':
                if ( name.equals("real_type") ) {
                    if ( !(value instanceof webbroker3.quoteadaptor.RealType) )
                        throw new IllegalArgumentException( "value for 'real_type' must be webbroker3.quoteadaptor.RealType: '"+value+"' is not." );
                    this.real_type = (webbroker3.quoteadaptor.RealType) value;
                    if (this.real_type_is_set)
                        this.real_type_is_modified = true;
                    this.real_type_is_set = true;
                    return;
                }
                break;
            case 's':
                if ( name.equals("strike_price") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'strike_price' must be Double: '"+value+"' is not." );
                    this.strike_price = (Double) value;
                    if (this.strike_price_is_set)
                        this.strike_price_is_modified = true;
                    this.strike_price_is_set = true;
                    return;
                }
                break;
            case 'v':
                if ( name.equals("volume") ) {
                    if ( value != null )
                      if ( !(value instanceof Double) )
                        throw new IllegalArgumentException( "value for 'volume' must be Double: '"+value+"' is not." );
                    this.volume = (Double) value;
                    if (this.volume_is_set)
                        this.volume_is_modified = true;
                    this.volume_is_set = true;
                    return;
                }
                else if ( name.equals("volume_time") ) {
                    if ( value != null )
                      if ( !(value instanceof String) )
                        throw new IllegalArgumentException( "value for 'volume_time' must be String: '"+value+"' is not." );
                    this.volume_time = (String) value;
                    if (this.volume_time_is_set)
                        this.volume_time_is_modified = true;
                    this.volume_time_is_set = true;
                    return;
                }
                break;
        }
        throw new IllegalArgumentException("field '"+name+"' not found.");
    }

}
@
