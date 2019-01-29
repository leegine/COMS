head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderVoucherCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���������`�[CSV(WEB3FeqOrderVoucherCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �s�p (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
Revesion History : 2008/01/23 �đo�g(���u) ���f��No.372
Revesion History : 2008/02/02 �đo�g(���u) ���f��No.396
*/

package webbroker3.feq;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MqStatusDef;
import webbroker3.common.define.WEB3OrderExecStatusDef;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3TaxTypeSpecialDef;
import webbroker3.common.define.WEB3TransactionTypeDef;
import webbroker3.feq.data.FeqOrderChangeStatusRow;
import webbroker3.feq.define.WEB3FeqChangeCancelDivDef;
import webbroker3.feq.define.WEB3FeqOrderAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (���������`�[CSV)<BR>
 * ���������`�[CSV�N���X<BR>
 *   
 * @@author �s�p
 * @@version 1.0
 */
public class WEB3FeqOrderVoucherCSV extends WEB3GentradeCsvDataModel 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOrderVoucherCSV.class);
            
    /**
     * (�،���ЃR�[�h���x��)<BR>
     * �،���ЃR�[�h���x��<BR>
     */
    public String institutionCodeLabel = "�،���ЃR�[�h";
    
    /**
     * (���X�R�[�h���x��)<BR>
     * ���X�R�[�h���x��<BR>
     */
    public String branchCodeLabel = "���X�R�[�h";
    
    /**
     * (�����ԍ����x��)<BR>
     * �����ԍ����x��<BR>
     */
    public String accountNoLabel = "�����ԍ�";
    
    /**
     * (���҃R�[�h���x��)<BR>
     * ���҃R�[�h���x��<BR>
     */
    public String traderCodeLabel = "���҃R�[�h";
    
    /**
     * (���ʃR�[�h���x��)<BR>
     * ���ʃR�[�h���x��<BR>
     */
    public String orderRequestNumberLabel = "���ʃR�[�h";
    
    /**
     * (�`�[No���x��)<BR>
     * �`�[No���x��<BR>
     */
    public String voucherNoLabel = "�`�[No";
    
    /**
     * (�����V�[�P���XNo���x��)<BR>
     * �����V�[�P���XNo���x��<BR>
     */
    public String orderSequenceNoLabel = "�����V�[�P���XNo";
    
    /**
     * (�����R�[�h���x��)<BR>
     * �����R�[�h���x��<BR>
     */
    public String productCodeLabel = "�����R�[�h";
    
    /**
     * (���n�����R�[�h���x��)<BR>
     * ���n�����R�[�h���x��<BR>
     */
    public String offshoreProductCodeLabel = "���n�����R�[�h";
    
    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    public String productNameLabel = "������";
    
    /**
     * (�s��R�[�h���x��)<BR>
     * �s��R�[�h���x��<BR>
     */
    public String marketCodeLabel = "�s��R�[�h";
    
    /**
     * (�s�ꖼ���x��)<BR>
     * �s�ꖼ���x��<BR>
     */
    public String marketNameLabel = "�s�ꖼ";
    
    /**
     * (�������x��)<BR>
     * �������x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 1�F���t<BR>
     * 2�F���t<BR>
     */
    public String tradeLabel = "����";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public String orderQuantityLabel = "��������";
    
    /**
     * (�w�l�E���s���x��)<BR>
     * �w�l�E���s���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F���s<BR>
     * 1�F�w�l<BR>
     */
    public String limitPriceOrMarketPriceLabel = "�w�l�E���s";
    
    /**
     * (�P�����x��)<BR>
     * �P�����x��<BR>
     */
    public String priceLabel = "�P��";
    
    /**
     * (���ϋ敪���x��)<BR>
     * ���ϋ敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F�~�݌���<BR>
     * 1�F�O�݌���<BR>
     */
    public String settleDivLabel = "���ϋ敪";
    
    /**
     * (�ʉ݃R�[�h���x��)<BR>
     * �ʉ݃R�[�h���x��<BR>
     */
    public String currencyCodeLabel = "�ʉ݃R�[�h";
    
    /**
     * (�ʉݖ��̃��x��)<BR>
     * �ʉݖ��̃��x��<BR>
     */
    public String currencyNameLabel = "�ʉݖ���";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public String orderTimestampLabel = "��������";
    
    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    public String bizDateLabel = "������";
    
    /**
     * (���s�������x��)<BR>
     * ���s�������x��<BR>
     */
    public String execCondLabel = "���s����";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public String orderCondLabel = "��������";
    
    /**
     * (�����������Z�q���x��)<BR>
     * �����������Z�q���x��<BR>
     */
    public String orderCondOperatorLabel = "�����������Z�q";
    
    /**
     * (���������P�����x��)<BR>
     * ���������P�����x��<BR>
     */
    public String orderCondPriceLabel = "���������P��";
    
    /**
     * (�iW�w�l�j�����w�l���x��)<BR>
     * �iW�w�l�j�����w�l���x��<BR>
     */
    public String wLimitPriceLabel = "�iW�w�l�j�����w�l";
    
    /**
     * (������ԃ��x��)<BR>
     * ������ԃ��x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F��t����<BR>
     * 1�F��t��<BR>
     * 2�F��t�G���[<BR>
     * 9�F�����E������ꂽ�f�[�^<BR>
     */
    public String orderStatusLabel = "�������";
    
    /**
     * (���敪���x��)<BR>
     * ���敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F�����<BR>
     * 1�F�ꕔ���<BR>
     * 2�F�S�����<BR>
     * 3�F����<BR>
     */
    public String execDivLabel = "���敪";
    
    /**
     * (��������敪���x��)<BR>
     * ��������敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F�����l<BR>
     * 1�F�����<BR>
     * 2�F�ꕔ�������<BR>
     * 3�F�S���������<BR>
     * 4�F������s<BR>
     * 5�F������<BR>
     * 6�F�ꕔ��������<BR>
     * 7�F�S����������<BR>
     * 8�F�������s<BR>
     * 9�F������V�K����<BR>
     */
    public String changeCancelDivLabel = "��������敪";
    
    /**
     * (�����`���l�����x��)<BR>
     * �����`���l�����x��<BR>
     */
    public String orderChanelLabel = "�����`���l��";
    
    /**
     * (�t�@@�N�^�[���x��)<BR>
     * �t�@@�N�^�[���x��<BR>
     */
    public String factorLabel = "�t�@@�N�^�[";
    
    /**
     * (�萔��No���x��)<BR>
     * �萔��No���x��<BR>
     */
    public String commisionNumberLabel = "�萔��No";
    
    /**
     * (�萔��No�}�ԃ��x��)<BR>
     * �萔��No�}�ԃ��x��<BR>
     */
    public String commisionBranchNumberLabel = "�萔��No�}��";
    
    /**
     * (���i�R�[�h���x��)<BR>
     * ���i�R�[�h���x��<BR>
     */
    public String productNumberLabel = "���i�R�[�h";
    
    /**
     * (�����o�H�敪���x��)<BR>
     * �����o�H�敪���x��<BR>
     */
    public String orderRootDivLabel = "�����o�H�敪";
    
    /**
     * (���[���敪���x��)<BR>
     * ���[���敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F�����M<BR>
     * 1�F���M��<BR>
     */
    public String mailDivLabel = "���[���敪";
    
    /**
     * (��������敪���x��)<BR>
     * ��������敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * 0�F���<BR>
     * 1�F����<BR>
     */
    public String taxTypeLabel = "��������敪";
    
    /**
     * (�^�p�R�[�h���x��)<BR>
     * �^�p�R�[�h���x��<BR>
     */
    public String orderEmpCodeLabel = "�^�p�R�[�h";
    
    /**
     * (���������`�[CSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * <BR>
     * �P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����B<BR>
     * @@roseuid 42A038C30156
     */
    public WEB3FeqOrderVoucherCSV() 
    {
        //�P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B
        super();                        
        
        //�Q�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����B
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()<BR>
     * �ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�،���ЃR�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�،���ЃR�[�h���x��<BR>
     *    �J�����ԍ��F 0<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�j���X�R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���X�R�[�h���x��<BR>
     *    �J�����ԍ��F 1<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�j�����ԍ�<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����ԍ����x��<BR>
     *    �J�����ԍ��F 2<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �S�j���҃R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���҃R�[�h���x��<BR>
     *    �J�����ԍ��F 3<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �T�j���ʃR�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���ʃR�[�h���x��<BR>
     *    �J�����ԍ��F 4<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �U�j�`�[No<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�`�[No���x��<BR>
     *    �J�����ԍ��F 5<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �V�j�����V�[�P���XNo<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����V�[�P���XNo���x��<BR>
     *    �J�����ԍ��F 6<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �W�j�����R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����R�[�h���x��<BR>
     *    �J�����ԍ��F 7<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �X�j���n�����R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���n�����R�[�h���x��<BR>
     *    �J�����ԍ��F 8<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�O�j������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������x��<BR>
     *    �J�����ԍ��F 9<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�P�j�s��R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�s��R�[�h���x��<BR>
     *    �J�����ԍ��F 10<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�Q�j�s�ꖼ<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�s�ꖼ���x��<BR>
     *    �J�����ԍ��F 11<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�R�j����<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�������x��<BR>
     *    �J�����ԍ��F 12<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�S�j��������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     *    �J�����ԍ��F 13<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�iint�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�T�j�w�l�E���s<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�w�l�E���s���x��<BR>
     *    �J�����ԍ��F 14<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�U�j�P��<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�P�����x��<BR>
     *    �J�����ԍ��F 15<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�V�j���ϋ敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���ϋ敪���x��<BR>
     *    �J�����ԍ��F 16<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�W�j�ʉ݃R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�ʉ݃R�[�h���x��<BR>
     *    �J�����ԍ��F 17<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �P�X�j�ʉݖ���<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�ʉݖ��̃��x��<BR>
     *    �J�����ԍ��F 18<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�O�j��������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     *    �J�����ԍ��F 19<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���t����<BR>
     *    ���t�t�H�[�}�b�g�F new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �Q�P�j������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������x��<BR>
     *    �J�����ԍ��F 20<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�Q�j���s����<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���s�������x��<BR>
     *    �J�����ԍ��F 21<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�R�j��������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     *    �J�����ԍ��F 22<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�S�j�����������Z�q<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������Z�q���x��<BR>
     *    �J�����ԍ��F 23<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�T�j���������P��<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������P�����x��<BR>
     *    �J�����ԍ��F 24<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�U�j�iW�w�l�j�����w�l<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�iW�w�l�j�����w�l���x��<BR>
     *    �J�����ԍ��F 25<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�V�j�������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.������ԃ��x��<BR>
     *    �J�����ԍ��F 26<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�W�j���敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���敪���x��<BR>
     *    �J�����ԍ��F 27<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�X�j��������敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.��������敪���x��<BR>
     *    �J�����ԍ��F 28<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�O�j�����`���l��<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����`���l�����x��<BR>
     *    �J�����ԍ��F 29<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�P�j�t�@@�N�^�[<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�t�@@�N�^�[���x��<BR>
     *    �J�����ԍ��F 30<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�Q�j�萔��No<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�萔��No���x��<BR>
     *    �J�����ԍ��F 31<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�R�j�萔��No�}��<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�萔��No�}�ԃ��x��<BR>
     *    �J�����ԍ��F 32<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�S�j���i�R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���i�R�[�h���x��<BR>
     *    �J�����ԍ��F 33<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�T�j�����o�H�敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����o�H�敪���x��<BR>
     *    �J�����ԍ��F 34<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�U�j���[���敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���[���敪���x��<BR>
     *    �J�����ԍ��F 35<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�V�j��������敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.��������敪���x��<BR>
     *    �J�����ԍ��F 36<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�W�j�^�p�R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�^�p�R�[�h���x��<BR>
     *    �J�����ԍ��F 37<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * @@roseuid 42A0399403B8
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 38;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //�P�j�،���ЃR�[�h 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.institutionCodeLabel, 
            0, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�Q�j���X�R�[�h 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.branchCodeLabel, 
            1, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�R�j�����ԍ�
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.accountNoLabel, 
            2, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�S�j���҃R�[�h
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.traderCodeLabel, 
            3, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�T�j���ʃR�[�h
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.orderRequestNumberLabel, 
            4, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�U�j�`�[No 
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.voucherNoLabel, 
            5, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�V�j�����V�[�P���XNo
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.orderSequenceNoLabel, 
            6, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�W�j�����R�[�h
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.productCodeLabel, 
            7, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�X�j���n�����R�[�h
        l_models[8] = new WEB3GentradeCsvColumnModel(
            this.offshoreProductCodeLabel, 
            8, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�O�j������
        l_models[9] = new WEB3GentradeCsvColumnModel(
            this.productNameLabel, 
            9, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�P�j�s��R�[�h
        l_models[10] = new WEB3GentradeCsvColumnModel(
            this.marketCodeLabel, 
            10, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�Q�j�s�ꖼ
        l_models[11] = new WEB3GentradeCsvColumnModel(
            this.marketNameLabel, 
            11, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�R�j����
        l_models[12] = new WEB3GentradeCsvColumnModel(
            this.tradeLabel, 
            12, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�S�j��������
        l_models[13] = new WEB3GentradeCsvColumnModel(
            this.orderQuantityLabel, 
            13, 
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null); 

        //�P�T�j�w�l�E���s
        l_models[14] = new WEB3GentradeCsvColumnModel(
            this.limitPriceOrMarketPriceLabel, 
            14, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�U�j�P��
        l_models[15] = new WEB3GentradeCsvColumnModel(
            this.priceLabel, 
            15, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //�P�V�j���ϋ敪
        l_models[16] = new WEB3GentradeCsvColumnModel(
            this.settleDivLabel, 
            16, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�P�W�j�ʉ݃R�[�h
        l_models[17] = new WEB3GentradeCsvColumnModel(
            this.currencyCodeLabel, 
            17, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);  

        //�P�X�j�ʉݖ���
        l_models[18] = new WEB3GentradeCsvColumnModel(
            this.currencyNameLabel, 
            18, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�O�j��������
        l_models[19] = new WEB3GentradeCsvColumnModel(
            this.orderTimestampLabel, 
            19, 
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")); 

        //�Q�P�j������
        l_models[20] = new WEB3GentradeCsvColumnModel(
            this.bizDateLabel, 
            20, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�Q�j���s����
        l_models[21] = new WEB3GentradeCsvColumnModel(
            this.execCondLabel, 
            21, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�R�j��������
        l_models[22] = new WEB3GentradeCsvColumnModel(
            this.orderCondLabel, 
            22, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�S�j�����������Z�q
        l_models[23] = new WEB3GentradeCsvColumnModel(
            this.orderCondOperatorLabel, 
            23, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�T�j���������P��
        l_models[24] = new WEB3GentradeCsvColumnModel(
            this.orderCondPriceLabel, 
            24, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //�Q�U�j�iW�w�l�j�����w�l
        l_models[25] = new WEB3GentradeCsvColumnModel(
            this.wLimitPriceLabel, 
            25, 
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null); 

        //�Q�V�j�������
        l_models[26] = new WEB3GentradeCsvColumnModel(
            this.orderStatusLabel, 
            26, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);  

        //�Q�W�j���敪
        l_models[27] = new WEB3GentradeCsvColumnModel(
            this.execDivLabel, 
            27, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�Q�X�j��������敪
        l_models[28] = new WEB3GentradeCsvColumnModel(
            this.changeCancelDivLabel, 
            28, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�O�j�����`���l��
        l_models[29] = new WEB3GentradeCsvColumnModel(
            this.orderChanelLabel, 
            29, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�P�j�t�@@�N�^�[
        l_models[30] = new WEB3GentradeCsvColumnModel(
            this.factorLabel, 
            30, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�Q�j�萔��No
        l_models[31] = new WEB3GentradeCsvColumnModel(
            this.commisionNumberLabel, 
            31, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�R�j�萔��No�}��
        l_models[32] = new WEB3GentradeCsvColumnModel(
            this.commisionBranchNumberLabel, 
            32, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�S�j���i�R�[�h
        l_models[33] = new WEB3GentradeCsvColumnModel(
            this.productNumberLabel, 
            33, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�T�j�����o�H�敪 
        l_models[34] = new WEB3GentradeCsvColumnModel(
            this.orderRootDivLabel, 
            34, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //�R�U�j���[���敪
        l_models[35] = new WEB3GentradeCsvColumnModel(
            this.mailDivLabel, 
            35, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�V�j��������敪
        l_models[36] = new WEB3GentradeCsvColumnModel(
            this.taxTypeLabel, 
            36, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null); 

        //�R�W�j�^�p�R�[�h
        l_models[37] = new WEB3GentradeCsvColumnModel(
            this.orderEmpCodeLabel, 
            37, 
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�،���ЃR�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�،���ЃR�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     * @@roseuid 42A9724E006C
     */
    public void setInstitutionCode(int l_intLineNo, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.institutionCodeLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strInstitutionCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���XID�ɊY�����镔�X.getBranchCode()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngBranchId - (���XID)<BR>
     * ���XID<BR>
     * @@roseuid 42A97334001D
     */
    public void setBranchCode(int l_intLineNo, long l_lngBranchId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setBranchCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.branchCodeLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            if (l_accountMgr == null)
            {
                log.debug("�A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�A�J�E���g�}�l�[�W�������݂��Ȃ��B");
            }
                
            Branch l_branch = l_accountMgr.getBranch(l_lngBranchId);//NotFoundException
                
            if (l_branch == null)
            {
                log.debug("���X�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���X�����݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_branch.getBranchCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set�����ԍ�)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����ԍ����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY������ڋq.get�\���ڋq�R�[�h()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 42A9737800F8
     */
    public void setAccountNo(int l_intLineNo, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccountNo(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.accountNoLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            WEB3GentradeAccountManager l_accountMgr = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
                
            if (l_accountMgr == null)
            {
                log.debug("�A�J�E���g�}�l�[�W�������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�A�J�E���g�}�l�[�W�������݂��Ȃ��B");
            }
                
            WEB3GentradeMainAccount l_mainAccount = 
                (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);//NotFoundException
                
            if (l_mainAccount == null)
            {
                log.debug("�ڋq�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�ڋq�����݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_mainAccount.getDisplayAccountCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set���҃R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���҃R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY�����鈵��.getTraderCode()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngTraderId - (����ID)<BR>
     * ����ID
     * @@roseuid 42A973F501F2
     */
    public void setTraderCode(int l_intLineNo, long l_lngTraderId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setTraderCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.traderCodeLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManager�����݂��Ȃ��B");
            }
            
            if (l_lngTraderId == 0)
            {
                this.setValue(l_intLineNo, l_model, null);
            }
            else
            {
                Trader l_trader = l_finObjMgr.getTrader(l_lngTraderId);//NotFoundException
                
                if (l_trader == null)
                {
                    log.debug("���҂����݂��Ȃ��B");
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3SystemLayerException(
                         WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                         this.getClass().getName() + STR_METHOD_NAME,
                         "���҂����݂��Ȃ��B");
                }
                
                this.setValue(l_intLineNo, l_model, l_trader.getTraderCode());
            }
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set���ʃR�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���ʃR�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���ʃR�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderRequestNumber - (���ʃR�[�h)<BR>
     * ���ʃR�[�h<BR>
     * @@roseuid 42A9746D00C9
     */
    public void setOrderRequestNumber(int l_intLineNo, String l_strOrderRequestNumber) 
    {
        final String STR_METHOD_NAME = " setOrderRequestNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderRequestNumberLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderRequestNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�`�[No)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�`�[No���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�`�[No<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strVoucheNo - (�`�[No)<BR>
     * �`�[No<BR>
     * @@roseuid 42A974960250
     */
    public void setVoucherNo(int l_intLineNo, String l_strVoucheNo) 
    {
        final String STR_METHOD_NAME = " setVoucherNo(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.voucherNoLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strVoucheNo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����V�[�P���XNo)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����V�[�P���XNo���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�����V�[�P���XNo<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderSequenceNo - (�����V�[�P���XNo)<BR>
     * �����V�[�P���XNo<BR>
     * @@roseuid 42A974BF02DD
     */
    public void setOrderSequenceNo(int l_intLineNo, String l_strOrderSequenceNo) 
    {
        final String STR_METHOD_NAME = " setOrderSequenceNo(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderSequenceNoLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderSequenceNo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY������O����������.getProductCode()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 42A9751E0166
     */
    public void setProductCode(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setProductCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.productCodeLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.exiting("TradingModule�����݂��Ȃ��B");
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModule�����݂��Ȃ��B");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManager�����݂��Ȃ��B");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("���������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���������݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getProductCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set���n�����R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���n�����R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY������O����������.get���n�����R�[�h()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 42A975980379
     */
    public void setOffshoreProductCode(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setOffshoreProductCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.offshoreProductCodeLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModule�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModule�����݂��Ȃ��B");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManager�����݂��Ȃ��B");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("���������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���������݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getOffshoreProductCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY������O����������.get�\��������()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 42A9763201B4
     */
    public void setProductName(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setProductName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.productNameLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModule�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModule�����݂��Ȃ��B");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManager�����݂��Ȃ��B");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("���������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���������݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_product.getDisplayProductName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set�s��R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�s��R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�s��ID�ɊY������s��.getMarketCode()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngMarketId - (�s��ID)<BR>
     * �s��ID<BR>
     * @@roseuid 42A9767201E3
     */
    public void setMarketCode(int l_intLineNo, long l_lngMarketId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMarketCode(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.marketCodeLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManager�����݂��Ȃ��B");
            }
                
            Market l_market = l_finObjMgr.getMarket(l_lngMarketId);//NotFoundException
                
            if (l_market == null)
            {
                log.debug("�s�ꂪ���݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�s�ꂪ���݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_market.getMarketCode());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set�s�ꖼ)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�s�ꖼ���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�s��ID�ɊY������s��.getMarketName()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngMarketId - (�s��ID)<BR>
     * �s��ID<BR>
     * @@roseuid 42A9772B03A8
     */
    public void setMarketName(int l_intLineNo, long l_lngMarketId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMarketName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.marketNameLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            FinObjectManager l_finObjMgr = (FinObjectManager)l_finApp.getFinObjectManager();
                
            if (l_finObjMgr == null)
            {
                log.debug("FinObjectManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinObjectManager�����݂��Ȃ��B");
            }
                
            Market l_market = l_finObjMgr.getMarket(l_lngMarketId);//NotFoundException
                
            if (l_market == null)
            {
                log.debug("�s�ꂪ���݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "�s�ꂪ���݂��Ȃ��B");
            }
                
            this.setValue(l_intLineNo, l_model, l_market.getMarketName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set����)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.is���t == true �̏ꍇ�A"2"<BR>
     *          ����.is���t == false �̏ꍇ�A"1"<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t�������ǂ����̃t���O<BR>
     * @@roseuid 42A977C00270
     */
    public void setTrade(int l_intLineNo, boolean l_blnIsBuy) 
    {
        final String STR_METHOD_NAME = " setTrade(int, boolean) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.tradeLabel);
        
        //�Q�j�@@�l�Z�b�g
        if (l_blnIsBuy)
        {
            this.setValue(l_intLineNo, l_model, WEB3TransactionTypeDef.BUY);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3TransactionTypeDef.SELL);
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.��������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@roseuid 42A97EE70108
     */
    public void setOrderQuantity(int l_intLineNo, long l_lngOrderQuantity) 
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, long) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderQuantityLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, new Long(l_lngOrderQuantity));       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�w�l�E���s)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�w�l�E���s���x��<BR><BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�ȉ��̂Ƃ���j<BR>
     *         ����.�w�l == 0 �̏ꍇ�A�h���s�h<BR>
     *         ����.�w�l != 0 �̏ꍇ�A�h�w�l�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@roseuid 42A97F2303C7
     */
    public void setLimitPriceOrMarketPrice(int l_intLineNo, double l_lngLimitPrice) 
    {
        final String STR_METHOD_NAME = " setLimitPriceOrMarketPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.limitPriceOrMarketPriceLabel);
        
        //�Q�j�@@�l�Z�b�g
        if (l_lngLimitPrice == 0)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderPriceDivDef.MARKET_PRICE);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderPriceDivDef.LIMIT_PRICE);
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�P��)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�P�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�w�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@roseuid 42A97F5A0389
     */
    public void setPrice(int l_intLineNo, double l_lngLimitPrice) 
    {
        final String STR_METHOD_NAME = " setPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.priceLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, new Double(l_lngLimitPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���ϋ敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���ϋ敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���ϋ敪<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strSettleDiv - (���ϋ敪)<BR>
     * ���ϋ敪<BR>
     * @@roseuid 42A97F8E032B
     */
    public void setSettleDiv(int l_intLineNo, String l_strSettleDiv) 
    {
        final String STR_METHOD_NAME = " setSettleDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.settleDivLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strSettleDiv);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ʉ݃R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�ʉ݃R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�ʉ݃R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strCurrencyCode - (�ʉ݃R�[�h)<BR>
     * �ʉ݃R�[�h<BR>
     * @@roseuid 42A97FD902BE
     */
    public void setCurrencyCode(int l_intLineNo, String l_strCurrencyCode) 
    {
        final String STR_METHOD_NAME = " setCurrencyCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.currencyCodeLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strCurrencyCode);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ʉݖ���)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�ʉݖ��̃��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY���������.get�ʉ�().get�ʉݖ�()�̖߂�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@roseuid 42A97FF5007C
     */
    public void setCurrencyName(int l_intLineNo, long l_lngProductId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCurrencyName(int, long) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.currencyNameLabel);
            
            //�Q�j�@@�l�Z�b�g
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
            if (l_finApp == null)
            {
                log.debug("FinApp�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "FinApp�����݂��Ȃ��B");
            }
            
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
            if (l_tradingModule == null)
            {
                log.debug("TradingModule�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "TradingModule�����݂��Ȃ��B");
            }
            
            ProductManager l_productMgr = (ProductManager)l_tradingModule.getProductManager();
                
            if (l_productMgr == null)
            {
                log.debug("ProductManager�����݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "ProductManager�����݂��Ȃ��B");
            }
                
            WEB3FeqProduct l_product = (WEB3FeqProduct)l_productMgr.getProduct(l_lngProductId);//NotFoundException
                
            if (l_product == null)
            {
                log.debug("���������݂��Ȃ��B");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                     WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                     this.getClass().getName() + STR_METHOD_NAME,
                     "���������݂��Ȃ��B");
            }
                
            WEB3GentradeCurrency l_currency = l_product.getCurrency();

            this.setValue(l_intLineNo, l_model, l_currency.getCurrencyName());
            
            log.exiting(STR_METHOD_NAME);
        }
        catch (NotFoundException l_ex)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
    }
    
    /**
     * (set��������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.��������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_datOrderTimestamp - (��������)<BR>
     * ��������<BR>
     * @@roseuid 42A9815A03E7
     */
    public void setOrderTimestamp(int l_intLineNo, Date l_datOrderTimestamp) 
    {
        final String STR_METHOD_NAME = " setOrderTimestamp(int, Date) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderTimestampLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_datOrderTimestamp);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strBizDate - (������)<BR>
     * ������<BR>
     * @@roseuid 42A9817A00D9
     */
    public void setBizDate(int l_intLineNo, String l_strBizDate) 
    {
        final String STR_METHOD_NAME = " setOrderTimestamp(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.bizDateLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strBizDate);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���s����)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���s�������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���s����<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strExecCond - (���s����)<BR>
     * ���s����<BR>
     * @@roseuid 42A981A10250
     */
    public void setExecCond(int l_intLineNo, String l_strExecCond) 
    {
        final String STR_METHOD_NAME = " setExecCond(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.execCondLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strExecCond);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.��������<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderCond - (��������)<BR>
     * ��������<BR>
     * @@roseuid 42A981CC0241
     */
    public void setOrderCond(int l_intLineNo, String l_strOrderCond) 
    {
        final String STR_METHOD_NAME = " setOrderCond(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderCond);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����������Z�q)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����������Z�q���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�����������Z�q<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderCondOperator - (�����������Z�q)<BR>
     * �����������Z�q<BR>
     * @@roseuid 42A981E302AE
     */
    public void setOrderCondOperator(int l_intLineNo, String l_strOrderCondOperator) 
    {
        final String STR_METHOD_NAME = " setOrderCondOperator(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondOperatorLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderCondOperator);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���������P��)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���������P�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���������P��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_orderCondPrice - (���������P��)<BR>
     * ���������P��<BR>
     * @@roseuid 42A981F90398
     */
    public void setOrderCondPrice(int l_intLineNo, double l_orderCondPrice) 
    {
        final String STR_METHOD_NAME = " setOrderCondPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondPriceLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, new Double(l_orderCondPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }

    public void setOrderCondPrice(int l_intLineNo) 
    {
        final String STR_METHOD_NAME = " setOrderCondPrice(int) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderCondPriceLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, null);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�iW�w�l�j�����w�l)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�iW�w�l�j�����w�l���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�iW�w�l�j�����w�l<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_wLimitPrice - (�iW�w�l�j�����w�l)<BR>
     * �iW�w�l�j�����w�l<BR>
     * @@roseuid 42A98269032B
     */
    public void setWLimitPrice(int l_intLineNo, double l_wLimitPrice) 
    {
        final String STR_METHOD_NAME = " setWLimitPrice(int, double) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.wLimitPriceLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, new Double(l_wLimitPrice));       
        
        log.exiting(STR_METHOD_NAME);
    }

    public void setWLimitPrice(int l_intLineNo) 
    {
        final String STR_METHOD_NAME = " setWLimitPrice(int) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.wLimitPriceLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, null);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.������ԃ��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F�i�ȉ��̂Ƃ���j<BR>
     *       ����.������Ԃ��ȉ��̏ꍇ�A�h��t���ρh���Z�b�g����B<BR>
     * <BR>
     *          ��t�ρi�V�K�����j�A�������i�V�K�����j<BR>
     *          ��t�ρi�ύX�����j�A�������i�ύX�����j<BR>
     *          ��t�ρi��������j�A�������i��������j<BR>
     * <BR>
     *       ����.������Ԃ��ȉ��̏ꍇ�A�h��t�ρh���Z�b�g����B<BR> 
     * <BR>
     *          �����ρi�V�K�����j<BR> 
     * <BR>
     *       ����.������Ԃ��ȉ��̏ꍇ�A�h��t�G���[�h���Z�b�g����B<BR> 
     * <BR>
     *          �������s�i�V�K�����j<BR> 
     *          �������s�i�ύX�����j<BR> 
     *          �������s�i��������j<BR> 
     * <BR>
     *       ����.������Ԃ��ȉ��̏ꍇ�A�h�����E������ꂽ�f�[�^�h���Z�b�g����B<BR> 
     * <BR>
     *          �����ρi�ύX�����j<BR> 
     *          �����ρi��������j<BR> 
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderStatus - (�������)<BR>
     * �������<BR>
     * @@roseuid 42A9829F02DD
     */
    public void setOrderStatus(int l_intLineNo, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = " setOrderStatus(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderStatusLabel);
        
        //�Q�j�@@�l�Z�b�g
        String l_strOrderStatusInput = null;
        
        //����.������Ԃ��ȉ��̏ꍇ�A�h��t���ρh���Z�b�g����B
        //��t�ρi�V�K�����j�A�������i�V�K�����j
        //��t�ρi�ύX�����j�A�������i�ύX�����j
        //��t�ρi��������j�A�������i��������j
        if (Integer.toString(OrderStatusEnum.ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFY_ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCEL_ACCEPTED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.ORDERING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFYING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCELLING.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NAN;  
        }
        //����.������Ԃ��ȉ��̏ꍇ�A�h��t�ρh���Z�b�g����B
        //�����ρi�V�K�����j
        else if (Integer.toString(OrderStatusEnum.ORDERED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_NOT_NAN;  
        }
        //����.������Ԃ��ȉ��̏ꍇ�A�h��t�G���[�h���Z�b�g����B
        //�������s�i�V�K�����j
        //�������s�i�ύX�����j
        //�������s�i��������j
        else if (Integer.toString(OrderStatusEnum.NOT_ORDERED.intValue()).equals(l_strOrderStatus) || 
            Integer.toString(OrderStatusEnum.NOT_MODIFIED.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.NOT_CANCELLED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.EXEC_TYPE_ERROR;  
        }
        //����.������Ԃ��ȉ��̏ꍇ�A�h�����E������ꂽ�f�[�^�h���Z�b�g����B 
        //�����ρi�ύX�����j
        //�����ρi��������j
        else if (Integer.toString(OrderStatusEnum.MODIFIED.intValue()).equals(l_strOrderStatus) || 
            Integer.toString(OrderStatusEnum.CANCELLED.intValue()).equals(l_strOrderStatus))
        {
            l_strOrderStatusInput = WEB3FeqOrderAcceptTypeDef.CNANGE_CANCELED_DATA;  
        }
        
        this.setValue(l_intLineNo, l_model, l_strOrderStatusInput);       
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�ȉ��̂Ƃ���j<BR>
     *          ����.��萔�� == 0 �̏ꍇ�A�h�����h<BR>
     *          ����.��萔�� > 0 and ����.��萔�� < ����.�������� <BR>
     *          �̏ꍇ�A�h�ꕔ���h<BR>
     *          ����.��萔�� == ����.�������� �̏ꍇ�A�h�S�����h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngOrderQuantity - (��������)<BR>
     * ��������<BR>
     * 
     * @@param l_lngExecQuantity - (��萔��)<BR>
     * ��萔��<BR>
     * @@roseuid 42A982BA036A
     */
    public void setExecDiv(int l_intLineNo, long l_lngOrderQuantity, long l_lngExecQuantity) 
    {
        final String STR_METHOD_NAME = " setExecDiv(int, long, long) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.execDivLabel);
        
        //�Q�j�@@�l�Z�b�g
        if (l_lngExecQuantity == 0)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.UNEXECUTED);
        }
        else if (l_lngExecQuantity > 0 && l_lngExecQuantity < l_lngOrderQuantity)
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.PARTIALLY_EXECUTED);
        }  
        else if (l_lngOrderQuantity == l_lngExecQuantity) 
        {
            this.setValue(l_intLineNo, l_model, WEB3OrderExecStatusDef.EXECUTED);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��������敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.��������敪���x��<BR>
     * <BR>
     * �Q�j�h������V�K�����h���ǂ����̔���<BR>
     * <BR>
     * �ȉ��̏����ŁA�O�������󋵃e�[�u������������B<BR> 
     * <BR>
     * [����]<BR> 
     *   ����ID = ����.����ID <BR>
     *   �V�K����ID = ����.����ID <BR>
     * <BR>
     *   �Y�����R�[�h�����݂����ꍇ�A���̒����́h������V�K�����h�Ƃ���B<BR> 
     * <BR>
     * �R�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�ȉ��̂Ƃ���j<BR>
     *    �Q�j�Łh������V�K�����h�Ɣ��肳�ꂽ�ꍇ�A�h������V�K�����h<BR>
     *    ����ȊO�̏ꍇ�A����.��������敪 <BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_lngAccountId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_strChangeCancelDiv - (��������敪)<BR>
     * ��������敪<BR>
     * @@throws 
     * @@roseuid 42A982D401F3
     */
    public void setChangeCancelDiv(
        int l_intLineNo, 
        long l_lngAccountId, 
        long l_lngOrderId, 
        String l_strChangeCancelDiv) 
            throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setChangeCancelDiv(int, long, long, String) ";
        log.entering(STR_METHOD_NAME );

        try
        {
            //�P�j�@@�J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(this.changeCancelDivLabel);
        
            //�Q�j�h������V�K�����h���ǂ����̔���
            //�ȉ��̏����ŁA�O�������󋵃e�[�u������������B
            //[����]
            //����ID = ����.����ID
            //�V�K����ID = ����.����ID 
            String l_strWhere = " account_id = ? and new_order_id = ? ";
            Object[] l_objs = {new Long(l_lngAccountId), new Long(l_lngOrderId)};
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();//DataNetworkException,DataQueryException    
            List l_lisFeqOrderChangeStatusRows = l_queryProcessor.doFindAllQuery(
                FeqOrderChangeStatusRow.TYPE, 
                l_strWhere,
                l_objs);//DataNetworkException, DataQueryException
            
            boolean l_blnIsNewOrderAfterChanged = false;
            
            //�Y�����R�[�h�����݂����ꍇ�A���̒����́h������V�K�����h�Ƃ���B    
            if (l_lisFeqOrderChangeStatusRows != null && 
                !l_lisFeqOrderChangeStatusRows.isEmpty())
            {
                l_blnIsNewOrderAfterChanged = true;
            }
        
            //�R�j�@@�l�Z�b�g
            if (l_blnIsNewOrderAfterChanged)
            {
                //�Q�j�Łh������V�K�����h�Ɣ��肳�ꂽ�ꍇ�A�h������V�K�����h
                this.setValue(l_intLineNo, l_model, WEB3FeqChangeCancelDivDef.CHANGED_NEW_ORDER);
            }
            else
            {
                //����ȊO�̏ꍇ�A����.��������敪
                this.setValue(l_intLineNo, l_model, l_strChangeCancelDiv);
            }            
        
            log.exiting(STR_METHOD_NAME);
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        catch (DataQueryException l_ex)
        {
            log.error("DB�ւ̃A�N�Z�X�Ɏ��s���܂����B", l_ex);

            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

    }
    
    /**
     * (set�����`���l��)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����`���l�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�����`���l��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderChanel - (�����`���l��)<BR>
     * �����`���l��<BR>
     * @@roseuid 42A982EE0212
     */
    public void setOrderChanel(int l_intLineNo, String l_strOrderChanel) 
    {
        final String STR_METHOD_NAME = " setOrderChanel(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderChanelLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderChanel);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�t�@@�N�^�[)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�t�@@�N�^�[���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�t�@@�N�^�[<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strFactor - (�t�@@�N�^�[)<BR>
     * �t�@@�N�^�[<BR>
     * @@roseuid 42A9830B01B4
     */
    public void setFactor(int l_intLineNo, String l_strFactor) 
    {
        final String STR_METHOD_NAME = " setFactor(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.factorLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strFactor);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�萔��No)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�萔��No���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�萔��No<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strCommisionNumber - (�萔��No)<BR>
     * �萔��No<BR>
     * @@roseuid 42A9832401E3
     */
    public void setCommisionNumber(int l_intLineNo, String l_strCommisionNumber) 
    {
        final String STR_METHOD_NAME = " setCommisionNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.commisionNumberLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strCommisionNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�萔��No�}��)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�萔��No�}�ԃ��x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�萔��No�}��<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strCommisionBranchNumber - (�萔��No�}��)<BR>
     * �萔��No�}��<BR>
     * @@roseuid 42A9833F027F
     */
    public void setCommisionBranchNumber(int l_intLineNo, String l_strCommisionBranchNumber) 
    {
        final String STR_METHOD_NAME = " setCommisionBranchNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.commisionBranchNumberLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strCommisionBranchNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���i�R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���i�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.���i�R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strProductNumber - (���i�R�[�h)<BR>
     * ���i�R�[�h<BR>
     * @@roseuid 42A9835D006C
     */
    public void setProductNumber(int l_intLineNo, String l_strProductNumber) 
    {
        final String STR_METHOD_NAME = " setProductNumber(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.productNumberLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strProductNumber);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����o�H�敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�����o�H�敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�����o�H�敪<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderRootDiv - (�����o�H�敪)<BR>
     * �����o�H�敪<BR>
     * @@roseuid 42A9837E0398
     */
    public void setOrderRootDiv(int l_intLineNumber, String l_strOrderRootDiv) 
    {
        final String STR_METHOD_NAME = " setOrderRootDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderRootDivLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_model, l_strOrderRootDiv);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���[���敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.���[���敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�ȉ��̂Ƃ���j<BR>
     *         ����.������� == �i�h�������i�V�K�����j�h or �h�������i���������j�h or<BR>
     * �@@�@@�@@�@@ �h�������i��������j�h�j �̏ꍇ�A�h�����M�h<BR>
     *         ����ȊO�̏ꍇ�A�h���M�����h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderStatus - (�������)<BR>
     * �������<BR>
     * @@roseuid 42A9839A033B
     */
    public void setMailDiv(int l_intLineNo, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = " setMailDiv(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.mailDivLabel);
        
        //�Q�j�@@�l�Z�b�g
        if (Integer.toString(OrderStatusEnum.ORDERING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.MODIFYING.intValue()).equals(l_strOrderStatus) ||
            Integer.toString(OrderStatusEnum.CANCELLING.intValue()).equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNo, l_model, WEB3MqStatusDef.NOT_SEND_MAIL);
        }
        else
        {
            this.setValue(l_intLineNo, l_model, WEB3MqStatusDef.MAIL_SENDED);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��������敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.��������敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F �i�ȉ��̂Ƃ���j<BR>
     *       ����.��������敪�i�����P��.�ŋ敪�j == �h��ʁh �̏ꍇ�A�h��ʁh<BR>
     *       ����.��������敪�i�����P��.�ŋ敪�j == �h����h �̏ꍇ�A�h����h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strTaxType - (��������敪)<BR>
     * ��������敪<BR>
     * @@roseuid 42A983F40231
     */
    public void setTaxType(int l_intLineNo, String l_strTaxType) 
    {
        final String STR_METHOD_NAME = " setTaxType(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.taxTypeLabel);
        
        //�Q�j�@@�l�Z�b�g
        String l_strTaxTypeInput = null;
        //����.��������敪�i�����P��.�ŋ敪�j == �h��ʁh �̏ꍇ�A�h��ʁh
        if (Integer.toString(TaxTypeEnum.NORMAL.intValue()).equals(l_strTaxType))
        {
            l_strTaxTypeInput = WEB3TaxTypeSpecialDef.NORMAL;
        }
        else if (Integer.toString(TaxTypeEnum.SPECIAL.intValue()).equals(l_strTaxType))
        {
            l_strTaxTypeInput = WEB3TaxTypeSpecialDef.SPECIAL;
        }
        
        this.setValue(l_intLineNo, l_model, l_strTaxTypeInput);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�^�p�R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ���������`�[CSV.�^�p�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�^�p�R�[�h<BR>
     * @@param l_intLineNo - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@roseuid 42A98411035A
     */
    public void setOrderEmpCode(int l_intLineNo, String l_strOrderEmpCode) 
    {
        final String STR_METHOD_NAME = " setOrderEmpCode(int, String) ";
        log.entering(STR_METHOD_NAME );

        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(this.orderEmpCodeLabel);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNo, l_model, l_strOrderEmpCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getCSV�t�@@�C���s)<BR>
     * �igetCSV�t�@@�C���s�̃I�[�o�[���C�h�j<BR>
     * <BR>
     * CSV�t�@@�C���ɏo�͂���f�[�^���A�s���̔z��ɂĕԋp����B<BR>
     * <BR>
     * �P�j�X�[�p�[�N���X��getCSV�t�@@�C���s()���\�b�h���R�[������B<BR>
     * <BR>
     * �Q�j�擾�����z�񂩂��s�A�u�����N�݂̂̍s�����O����B<BR>
     * <BR>
     * �R�j�Q�j�̌��ʂ�ԋp����B<BR>
     * @@return String[]
     * @@roseuid 42AFD4F5007C
     */
    public String[] getCSVFileRow() 
    {
        //�P�j�X�[�p�[�N���X��getCSV�t�@@�C���s()���\�b�h���R�[������B
        String[] l_strCsvFileLines = super.getCsvFileLines();
        
        //�Q�j�擾�����z�񂩂��s�A�u�����N�݂̂̍s�����O����B
        List l_lisLines = new ArrayList();
        String[] l_strLines = null;
        
        if (l_strCsvFileLines != null && l_strCsvFileLines.length > 0)
        {
            int l_intLinesCnt = l_strCsvFileLines.length;
            for (int i = 0; i < l_intLinesCnt; i++)
            {
                if (!WEB3StringTypeUtility.isEmptyOrBlank(l_strCsvFileLines[i]))
                {
                    l_lisLines.add(l_strCsvFileLines[i]);
                }
            }
            
            if (l_lisLines.size() > 0)
            {
                l_strLines = new String[l_lisLines.size()];
                l_lisLines.toArray(l_strLines);
            }
        }         
        
        //�R�j�Q�j�̌��ʂ�ԋp����B
        return l_strLines;
    }
    
    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�s�̏o�͗v�ۂ𔻒肷��B<BR>
     * �i�I�[�o�[���C�h���\�b�h�j<BR>
     * <BR>
     * false��ԋp����B<BR>
     * 
     * @@return boolean 
     */
    public boolean isColumnHeadOutput()
    {
        return false;
    }
}
@
