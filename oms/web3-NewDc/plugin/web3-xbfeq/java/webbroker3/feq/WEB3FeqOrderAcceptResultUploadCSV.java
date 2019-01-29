head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqOrderAcceptResultUploadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O�������������ʈꊇ����CSV(WEB3FeqOrderAcceptResultUploadCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 �A�C��(���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[ 
                   2006/09/18 �����(���u) �d�l�ύX�E���f��245,273    
Revesion History : 2009/08/03 �Ԑi(���u) �d�l�ύX�E���f��516
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O�������������ʈꊇ����CSV)<BR>
 * �O�������������ʈꊇ����CSV<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptResultUploadCSV extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOrderAcceptResultUploadCSV.class);
    
    /**
     * (�^�p�R�[�h���x��)<BR>
     * �^�p�R�[�h���x��<BR>
     */
    private static  String EMP_CODE_LABEL = "�^�p�R�[�h";
    
    /**
     * (�����R�[�h���x��)<BR>
     * �����R�[�h���x��<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "�����R�[�h";
    
    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    private static  String PRODUCT_NAME_LABEL = "������";
    
    /**
     * (�������x��)<BR>
     * �������x��<BR>
     */
    private static  String TRADE_LABEL = "����";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    private static  String ORDER_QUANTITY_LABEL = "��������";
    
    /**
     * (�����@@���x��)<BR>
     * �����@@���x��<BR>
     */
    private static  String EXEC_METHOD_LABEL = "�����@@";
    
    /**
     * (�����P�����x��)<BR>
     * �����P�����x��<BR>
     */
    private static  String ORDER_PRICE_LABEL = "������P��";
    
	/**
	 * (�ŏI���ڃJ���}���x��)<BR>
	 * �ŏI���ڃJ���}���x��<BR>
	 */
	private static  String LAST_COMMA_LABEL = "�ŏI���ڃJ���}";

    /**
     * (����_��)<BR>
     * ����_��<BR>
     */
    private static  String TRADE_SELL = "S";
    
    /**
     * (����_��)<BR>
     * ����_��<BR>
     */
    private static  String TRADE_BUY = "B";
    
    /**
     * (�����@@_L)<BR>
     * �����@@_L<BR>
     */
    private static  String EXEC_METHOD_L = "L";
    
    /**
     * (�����P���G���[)<BR>
     * �����P���G���[�i�Ăђl�����j<BR>
     */
    private static  String ORDER_PRICE_ERROR = "�Ăђl����";
    
    /**
     * (���������G���[)<BR>
     * ���������G���[�i�[������j<BR>
     */
    private static  String ORDER_QUANTITY_ERROR = "�[������";
    
    /**
     * (�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �A�b�v���[�h�t�@@�C���h�c<BR>
     */
    private String UPLOAD_FILE_ID = "�O�������������ʈꊇ����";
    
    /**
     * (�O�������������ʈꊇ����CSV)<BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B<BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 429FE01C0243
     */
    public WEB3FeqOrderAcceptResultUploadCSV(long l_lngUploadId) 
    {
        this.administratorUploadId = l_lngUploadId;     
    }
    
    /**
     * (�O�������������ʈꊇ����CSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 429FE01C0242
     */
    public WEB3FeqOrderAcceptResultUploadCSV() 
    {
        this.createColumnHeader();     
    }
    
    /**
     * (create�J�����w�b�_ ())<BR>
     * �J�����w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR> 
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0�@@���^�p�R�[�h <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.�^�p�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1�@@�������R�[�h <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.�����R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2�@@��������<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.���������x�� <BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3�@@������ <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.�������x�� <BR>
     * �@@�J�����ԍ��F 3 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4�@@����������<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.t�����������x�� <BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5�@@�������@@<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.�����@@���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 6�@@�����P��<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.���P�����x�� <BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 7�@@���ŏI���ڃJ���}<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O�������������ʈꊇ����CSV.�ŏI���ڃJ���}���x�� <BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * @@roseuid 429FE01C0252
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0 �^�p�R�[�h
        l_models[0] = new WEB3GentradeCsvColumnModel(
            EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 1 �����R�[�h
        l_models[1] = new WEB3GentradeCsvColumnModel(
            PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 2 ������
        l_models[2] = new WEB3GentradeCsvColumnModel(
            PRODUCT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 3 ����
        l_models[3] = new WEB3GentradeCsvColumnModel(
            TRADE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 4 ��������
        l_models[4] = new WEB3GentradeCsvColumnModel(
            ORDER_QUANTITY_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 5 �����@@
        l_models[5] = new WEB3GentradeCsvColumnModel(
            EXEC_METHOD_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 6 ���P��
        l_models[6] = new WEB3GentradeCsvColumnModel(
            ORDER_PRICE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

		//index 7 �ŏI���ڃJ���}
		l_models[7] = new WEB3GentradeCsvColumnModel(
			LAST_COMMA_LABEL,
			7,
			WEB3GentradeCsvColumnModel.  STRINGTYPE,
			null);
        
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * �O�������������ʈꊇ����.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 429FE01C0253
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILE_ID;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v.�O��������ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 42C4E176008F
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.FOREIGN_EQUITY;
    }
    
    /**
     * (get�^�p�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̉^�p�R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĉ^�p�R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�^�p�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429FE01C0254
     */
    public String getEmpCode(int l_intLineNumber) 
    {
        String l_strEmpCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(EMP_CODE_LABEL));
        return l_strEmpCode;
    }
    
    /**
     * (get�����R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖����R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ����R�[�h���擾���ԋp����B<BR> 
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�����R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429FE01C0256
     */
    public String getProductCode(int l_intLineNumber) 
    {
        String l_strProductCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_CODE_LABEL));
        return l_strProductCode;
    }
    
    /**
     * (get������)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖����R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ��������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�����R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429FE1AE008D
     */
    public String getProductName(int l_intLineNumber) 
    {
        String l_strProductName = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_NAME_LABEL));
        return l_strProductName;
    }
    
    /**
     * (get����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̔������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429FE01C0258
     */
    public String getTrade(int l_intLineNumber) 
    {
        String l_strTrade = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(TRADE_LABEL));
        return l_strTrade;
    }
    
    /**
     * (get��������)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒����������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĒ����������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�����������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0261
     */
    public String getOrderQuantity(int l_intLineNumber) throws WEB3BaseException
    {
        String l_strOrderQuantity = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_QUANTITY_LABEL));
        return l_strOrderQuantity;
    }
    
    /**
     * (get�����@@)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖����@@���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ����@@���擾����B<BR> 
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�����@@���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429FE01C0265
     */
    public String getExecMethod(int l_intLineNumber) 
    {
        String l_strExecMethod = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(EXEC_METHOD_LABEL));
        return l_strExecMethod;
    }
    
    /**
     * (get�����P��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒����P�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĒ����P�����擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O�������������ʈꊇ����CSV.�����P�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0263
     */
    public String getOrderPrice(int l_intLineNumber) throws WEB3BaseException
    {
        String l_strOrderPrice = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_PRICE_LABEL));
        return l_strOrderPrice;
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�^�p�R�[�h�C�����P�ʂ̃`�F�b�N <BR>
     * �@@get�^�p�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get�^�p�R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�|�P�j�@@�^�p�R�[�h���擾�ł��Ȃ��ꍇ�iget�^�p�R�[�h() == null�j�A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02032<BR>
     * <BR>
     * �@@�P�|�Q�j�@@�V���̔��p�p�����łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02033<BR>
     * <BR>
     * �@@�P�|�R�j�@@��2byte������.�O�������^�p�R�[�h�̔ԋ敪�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_03164<BR>
     * <BR>�@@
     *   �P�|�S�j�@@�E5byte�������łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02035<BR>
     * <BR>
     * �@@�P�|�T�j�@@this.get�����P��()�@@�ɂĊO�����������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * <BR>
     * <BR>
     * �@@[get�����P��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�|�U�j�@@�����P�ʂ̃`�F�b�N<BR>
     * �@@�@@�@@�o���I����̒����̏ꍇ�i�P�|�T�j�Ŏ擾���������P��.is�o���I��() == true�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02166<BR>
     * <BR>
     * �Q�j�@@�����R�[�h�̃`�F�b�N<BR>
     * �@@get�����R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A<BR>
     * �����R�[�h���擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�Q�|�P�j�@@��������9byte���傫���ꍇ�i�����R�[�h.length > 9�j�́A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00439<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����P�ʂ̖����ƈ�v���Ȃ��ꍇ�i�ȉ��̏����ɓ��Ă͂Ȃ�Ȃ��ꍇ�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00301<BR>
     * <BR>
     * �@@[�����P�ʂ̖����ƈ�v�������]<BR>
     * �@@�����P��.getProduct().�����R�[�h == �����R�[�h�@@�܂��́A<BR>
     * �@@�����P��.getProduct().���n�����R�[�h == �����R�[�h<BR>
     * <BR>
     * �R�j�@@�������̃`�F�b�N<BR>
     * �@@get������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A���������擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get������()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@��������50byte���傫���ꍇ�i������.length > 50�j�́A��O���X���[����B <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00441<BR>
     * <BR>
     * �S�j�@@�����̃`�F�b�N<BR>
     * �@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�������擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�S�|�P�j�@@�������A�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01403<BR>
     * <BR>
     * �@@[�L���R�[�h�l]<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"S"�j�܂��́A<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"B"�j<BR>
     * <BR>
     * �@@�S�|�Q�j�@@�����P�ʂ̒�����ʂƑΉ�����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02167<BR>
     * <BR>
     * �@@[�Ή�����l]<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"B"�j�˒������.701�F�O������<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"S"�j�˒������.702�F�O������<BR>
     * <BR>
     * �T�j�@@���������̃`�F�b�N<BR>
     * �@@get��������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get��������()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00126<BR>
     * <BR>
     * �@@�T�|�Q�j�@@�i���l�̏ꍇ�j9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     * <BR>
     * �@@�T�|�R�j�@@�i���l�łȂ��ꍇ�j�O�������������ʈꊇ����CSV.���������G���[�ƈ�v���Ȃ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00901<BR>
     * <BR>
     * �U�j�@@�����@@�̃`�F�b�N<BR>
     * �@@get�����@@()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�����@@���擾�ł����ꍇ<BR>
     * �݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����@@()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�U�|�P�j�@@�����@@���O�������������ʈꊇ����CSV.�����@@_L�ƈ�v���Ȃ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02168<BR>
     * <BR>
     * �V�j�@@�����P���̃`�F�b�N<BR>
     * �@@get�����P��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get�����P��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02118<BR>
     * <BR>
     * �@@�V�|�Q�j�@@�i���l�̏ꍇ�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02120<BR>
     * <BR>
     * �@@�V�|�R�j�@@�i���l�̏ꍇ�j���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔͊O�ł���΁A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02093<BR>
     * <BR>
     * �@@�V�|�S�j�@@�i���l�łȂ��ꍇ�j�O�������������ʈꊇ����CSV.�����P���G���[�ƈ�v���Ȃ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02119<BR>
     * �@@�W�j�@@�I�����C�����̒��������̓A�b�v���[�h�ΏۊO
     * �@@�@@�@@�@@������Ԃ��V�F��t�ρi��������)�A�W�F�������i���������j�A�P�O�F�����ρi���������j�̏ꍇ
     * ��O���X���[����B
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02178
     * <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strFeqOrderEmpCodeManageDiv - (�O�������^�p�R�[�h�̔ԋ敪)<BR>
     * �O�������^�p�R�[�h�̔ԋ敪<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0267
     */
    public void validateDetailLine(int l_intLineNumber, 
        String l_strFeqOrderEmpCodeManageDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailLine(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�^�p�R�[�h�C�����P�ʂ̃`�F�b�N 
        //�@@get�^�p�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B 
        String l_strEmpCode = this.getEmpCode(l_intLineNumber); 

        //�@@�P�|�P�j�@@�^�p�R�[�h���擾�ł��Ȃ��ꍇ�iget�^�p�R�[�h() == null�j�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(l_strEmpCode))
        {
            String l_strMessage = "�^�p�R�[�h�������͂ł��B";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�@@�P�|�Q�j�@@�V���̔��p�p�����łȂ��ꍇ�A��O���X���[����B
        final int MANAGEMENTCODE_LEN = 7;
        if (!WEB3StringTypeUtility.isLetterAndDigit(l_strEmpCode) 
            || l_strEmpCode.length() != MANAGEMENTCODE_LEN)
        {
            log.debug("�^�p�R�[�h = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02033,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h���V���̔��p�p�����ł͂���܂���B");
        }

        //�@@�P�|�R�j�@@��2byte������.�O�������^�p�R�[�h�̔ԋ敪�łȂ��ꍇ�A��O���X���[����B
        final int LEFT_LEN = 2;
        String l_strLeft2Byte = l_strEmpCode.substring(0, LEFT_LEN);        
        if (!l_strLeft2Byte.equals(l_strFeqOrderEmpCodeManageDiv))
        {
            log.debug("�^�p�R�[�h = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03164,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h�̍�2byte������.�O�������^�p�R�[�h�̔ԋ敪�ł͂���܂���B");
        }

        //  �P�|�S�j�@@�E5byte�������łȂ��ꍇ�A��O���X���[����B
        final int RIGHT_LEN = 5;
        String l_strRight5Byte = l_strEmpCode.substring(
            l_strEmpCode.length() - RIGHT_LEN);
        if (!WEB3StringTypeUtility.isDigit(l_strRight5Byte))
        {
            log.debug("�^�p�R�[�h = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02035,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h�̉E5byte�������ł͂���܂���B");
        }

        //�@@�P�|�T�j�@@this.get�����P��()�@@�ɂĊO�����������P�ʃI�u�W�F�N�g���擾����B
        //�@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B
        WEB3FeqOrderUnit l_feqOrderUnit = this.getOrderUnit(l_intLineNumber);
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                "�O�����������P�ʃI�u�W�F�N�g���擾�ł��܂���B");
        }

        //�@@�P�|�U�j�@@�����P�ʂ̃`�F�b�N
        //�@@�@@�@@�o���I����̒����̏ꍇ�i�P�|�T�j�Ŏ擾���������P��.is�o���I��() == true�j�A
        //��O���X���[����B
        if (l_feqOrderUnit.isExecEnd())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02166,
                getClass().getName() + STR_METHOD_NAME,
                "�o���I����̒����́A������t���ʃA�b�v���[�h�s�ł��B");
        }

        //�Q�j�@@�����R�[�h�̃`�F�b�N
        //�@@get�����R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A
        //�����R�[�h���擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B
        String l_strProductCode = this.getProductCode(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strProductCode))
        {
            //�@@�Q�|�P�j�@@��������9byte���傫���ꍇ�i�����R�[�h.length > 9�j�́A��O���X���[����B 
            if (l_strProductCode.length() > 9)
            {
                String l_strMessage = "�����R�[�h�G���[�u" + l_strProductCode + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //�@@�Q�|�Q�j�@@�����P�ʂ̖����ƈ�v���Ȃ��ꍇ�i�ȉ��̏����ɓ��Ă͂Ȃ�Ȃ��ꍇ�j�A
            //��O���X���[����B
            FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
            if (l_feqProduct == null)
            {
                String l_strMessage = "�O���������������݂��Ȃ��B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            FeqProductRow  l_feqProductRow = (FeqProductRow)l_feqProduct.getDataSourceObject();
            if (!l_strProductCode.equals(l_feqProductRow.getProductCode())
                && !l_strProductCode.equals(l_feqProductRow.getOffshoreProductCode()))
            {
                String l_strMessage = "�����R�[�h�G���[�u" + l_strProductCode + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        //�R�j�@@�������̃`�F�b�N
        //�@@get������()�ɂāA�w��s�ԍ��̃f�[�^���擾���A���������擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B
        String l_strProductName = this.getProductName(l_intLineNumber);
        if (l_strProductName != null)
        {
            //�@@�R�|�P�j�@@��������50byte���傫���ꍇ�i������.length > 50�j�́A��O���X���[����B
            if (WEB3StringTypeUtility.getByteLength(l_strProductName) > 50)
            { 
                String l_strMessage = "�������G���[�u" + l_strProductName + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //�S�j�@@�����̃`�F�b�N
        //�@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�������擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B
        String l_strTrade = this.getTrade(l_intLineNumber);
        if (l_strTrade != null)
        {
            //�@@�S�|�P�j�@@�������A�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B
            if (!TRADE_SELL.equals(l_strTrade) && !TRADE_BUY.equals(l_strTrade))
            { 
                String l_strMessage = "�����G���[�u" + l_strTrade + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }

            //�@@�S�|�Q�j�@@�����P�ʂ̒�����ʂƑΉ�����l�łȂ��ꍇ�A��O���X���[����B
            OrderTypeEnum l_orderType = l_feqOrderUnit.getOrderType();
            if (TRADE_BUY.equals(l_strTrade) && !OrderTypeEnum.FEQ_BUY.equals(l_orderType))
            {
                String l_strMessage = "�����G���[�u" + l_strTrade + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02167,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            else if (TRADE_SELL.equals(l_strTrade) && !OrderTypeEnum.FEQ_SELL.equals(l_orderType))
            {
                String l_strMessage = "�����G���[�u" + l_strTrade + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02167,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //�T�j�@@���������̃`�F�b�N
        //�@@get��������()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B 
        String l_strOrderQuantity = this.getOrderQuantity(l_intLineNumber);

        //�@@�T�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strOrderQuantity))
        {
            String l_strMessage = "���������G���[�u" + l_strOrderQuantity + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        if (WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
        {
            //�@@�T�|�Q�j�@@�i���l�̏ꍇ�j9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B
            double l_dblOrderQuantity = Double.parseDouble(l_strOrderQuantity);
            if (!WEB3StringTypeUtility.isInteger(l_strOrderQuantity)
                || l_dblOrderQuantity > 999999999)
            {
                String l_strMessage = "���������G���[�u" + l_dblOrderQuantity + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00903,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            // �O�ȉ��̏ꍇ
			else if (!WEB3StringTypeUtility.isInteger(l_strOrderQuantity)
				|| l_dblOrderQuantity <= 0)
			{
				String l_strMessage = "���������G���[�u" + l_dblOrderQuantity + "�v";
				log.debug(l_strMessage);
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00709,
					this.getClass().getName() + STR_METHOD_NAME,
					l_strMessage);
			}

        }
        else
        {
            //�@@�T�|�R�j�@@�i���l�łȂ��ꍇ�j�O�������������ʈꊇ����CSV.���������G���[�ƈ�v���Ȃ��ꍇ�A
            //��O���X���[����B
            if (!ORDER_QUANTITY_ERROR.equals(l_strOrderQuantity))
            {
                String l_strMessage = "�������������l�ȊO�̒l�ł��B�u" + l_strOrderQuantity + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        //�U�j�@@�����@@�̃`�F�b�N
        //�@@get�����@@()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�����@@���擾�ł����ꍇ
        //�݈̂ȉ��̃`�F�b�N���s���B
        String l_strExecMethod = this.getExecMethod(l_intLineNumber);
        
        if (l_strExecMethod != null)
        {
            //�@@�U�|�P�j�@@�����@@���O�������������ʈꊇ����CSV.�����@@_L�ƈ�v���Ȃ��ꍇ�A
            //��O���X���[����B
            if (!EXEC_METHOD_L.equals(l_strExecMethod))
            {
                String l_strMessage = "�����@@�G���[�B�u" + l_strExecMethod + "�v";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02168,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //�V�j�@@�����P���̃`�F�b�N
        //�@@get�����P��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B
        String l_strOrderPrice = this.getOrderPrice(l_intLineNumber);
         
        //�@@�V�|�P�j�@@�����͂̏ꍇ�A��O���X���[����B
        if (WEB3StringTypeUtility.isEmpty(l_strOrderPrice))
        {
            String l_strMessage = "�����P���������͂ł�";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02118,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        if (WEB3StringTypeUtility.isNumber(l_strOrderPrice))
        {
            //�@@�V�|�Q�j�@@�i���l�̏ꍇ�j�}�C�i�X�l�̏ꍇ�A��O���X���[����B
            if (WEB3StringTypeUtility.isMinus(l_strOrderPrice))
            {
                String l_strMessage = "�����P����0�ȉ��̒l�ł��B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02120,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //�@@�V�|�R�j�@@�i���l�̏ꍇ�j���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔͊O�ł���΁A
            //��O���X���[����B
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOrderPrice) > 6 
                || WEB3StringTypeUtility.getFractionDigits(l_strOrderPrice) > 5)
            {
                String l_strMessage = "�����P���G���[�B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02093,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        else
        {
            //�@@�V�|�S�j�@@�i���l�łȂ��ꍇ�j�O�������������ʈꊇ����CSV.�����P���G���[�ƈ�v���Ȃ��ꍇ�A
            //��O���X���[����B
            if (!ORDER_PRICE_ERROR.equals(l_strOrderPrice))
            {
                String l_strMessage = "�����P���G���[�u" + l_strOrderPrice + "�v�B";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02119,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // �W�j�I�����C�����̒��������͑ΏۊO
        OrderStatusEnum l_orderStatus = l_feqOrderUnit.getOrderStatus();
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) |
            OrderStatusEnum.MODIFYING.equals(l_orderStatus) |
           (OrderStatusEnum.MODIFIED.equals(l_orderStatus) &&
            !l_confirmedPrice))
        {
            log.debug("�Y�����钍��ID�f�[�^�͑ΏۊO�ł��B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get�����P��)<BR>
     * �^�p�R�[�h�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �O�����������}�l�[�W��.get�L�������P��By�^�p�R�[�h()�ɂāA<BR>
     * �����P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * [get�L�������P��By�^�p�R�[�h()�Ɏw�肷�����]<BR>
     * �������F�@@TradingSystem.getSystemTimestamp()�̓��t<BR>
     * �^�p�R�[�h�F�@@get�^�p�R�[�h(�s�ԍ�)<BR>
     * <BR>
     * �擾�ł��Ȃ������ꍇ�́A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0269
     */
    public WEB3FeqOrderUnit getOrderUnit(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getOrderUnit(int)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        
        //get�O�����������}�l�[�W��
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinApp�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModule�����݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "�O�����������}�l�[�W�������݂��Ȃ��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�O�����������}�l�[�W��.get�L�������P��By�^�p�R�[�h()�ɂāA�����P�ʃI�u�W�F�N�g���擾����B
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Date l_datBizDate = WEB3DateUtility.toDay(l_tradingSystem.getSystemTimestamp());
        l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getValidOrderUnitByOrderEmpCode(
                l_datBizDate, 
                this.getEmpCode(l_intLineNumber));//WEB3BaseException
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "get�L�������P��By�^�p�R�[�h(" 
                + l_datBizDate 
                + ", " 
                + this.getEmpCode(l_intLineNumber) 
                + ")��null�B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnit;
    }   

	/**
	 * (add���׍s) <BR>
	 * �iaddRow�j<BR>
	 * <BR>
	 * �����̖��׍s�����񂪑S�ċ󕶎����`�F�b�N���A�󕶎��̏ꍇ��-1��ԋp���A
	 * �󕶎��łȂ��ꍇ��super.add���׍s���R�[�����A���̌��ʂ�ԋp����B
	 * <BR>
	 * 1�j���׍s���<BR>
	 * �@@���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA<BR>
	 * ��؂蕶�����Ƃ�token[]�ɕ�������B<BR>
	 * <BR>
	 * �@@token.length��0�̏ꍇ��-1��ԋp����B<BR>
	 * <BR>
	 * �@@token.length!=0�̏ꍇ�Asuper.add���׍s���R�[������B<BR>
	 * @@param l_rowString - ���׍s������<BR>
	 * <BR>
	 * @@return int
	 * @@roseuid 40F4EFBD0354
	 */
	public int addRow(String l_rowString) throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "addRow(String)";
		log.entering(STR_METHOD_NAME);       
        
		//1�j�@@���׍s���
		//���׍s������.split(CSV�f�[�^���f��.��؂蕶��)�ɂāA
		//��؂蕶�����Ƃ�token[]�ɕ�������B
		String[] token = l_rowString.split(regex);
		// ���׍s�̍��ڑS�Ă���̏ꍇ�@@-1��ԋp����B
		if (token.length == 0)
		{
				return -1;
		}
		else
		{
			 int l_intLineNumbersuper = super.addRow(l_rowString);
			 return l_intLineNumbersuper;
		}
	}
    
    /**
     * (get��t�敪) <BR>
     * ��t�敪��ԋp����B<BR>
     * <BR>
     * �P�j�P���G���[�ithis.get�����P��(�s�ԍ�) == �O�������������ʈꊇ����CSV.�����P���G���[�j�܂��́A 
     * �����G���[�ithis.get��������(�s�ԍ�) == �O�������������ʈꊇ����CSV.���������G���[�j�̏ꍇ 
     * <BR>
     * �@@02�i������t�G���[�j��ԋp����B<BR>
     * <BR>
     * �Q�j��L�ȊO�̏ꍇ�A<BR>
     * <BR>
     * �@@01�i������t�ρj��ԋp����B<BR>
     * <BR>
     * @@param l_int - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 40F4EFBD0354
     */
    public String getAcceptDiv(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptDiv(int)";
        log.entering(STR_METHOD_NAME);       

        if (ORDER_PRICE_ERROR.equals(this.getOrderPrice(l_intLineNo)) 
            || ORDER_QUANTITY_ERROR.equals(this.getOrderQuantity(l_intLineNo)))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE;
        }
    }
}
@
