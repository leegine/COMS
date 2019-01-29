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
filename	WEB3FeqExecuteResultInputCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��������茋�ʈꊇ����CSV(WEB3FeqExecuteResultInputCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 �A�C��(���u) �V�K�쐬
Revesion History : 2009/08/03 �Ԑi(���u) �d�l�ύX�E���f��514
*/

package webbroker3.feq;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
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
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (�O��������茋�ʈꊇ����CSV)<BR>
 * �O��������茋�ʈꊇ����CSV<BR>
 *
 * @@author �A�C��
 * @@version 1.0
 */
public class WEB3FeqExecuteResultInputCSV extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqExecuteResultInputCSV.class);
    
    /**
     * (�^�p�R�[�h���x��)<BR>
     * �^�p�R�[�h���x��<BR>
     */
    private static  String ORDER_EMP_CODE_LABEL = "�^�p�R�[�h";
    
    /**
     * (�����R�[�h���x��)<BR>
     * �����R�[�h���x��<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "�����R�[�h";
    
    /**
     * (�������x��)<BR>
     * �������x��<BR>
     */
    private static  String TRADE_LABEL = "����";
    
    /**
     * (��芔�����x��)<BR>
     * ��芔�����x��<BR>
     */
    private static  String EXEC_QUANTITY_LABEL = "��芔��";
    
    /**
     * (���P�����x��)<BR>
     * ���P�����x��<BR>
     */
    private static  String EXEC_PRICE_LABEL = "���P��";
    
    /**
     * (���No.���x��)<BR>
     * ���No.���x��<BR>
     */
    private static  String EXEC_NO_LABEL = "���No.";
    
    /**
     * (FILLER���x��)<BR>
     * FILLER���x��<BR>
     */
    private static  String FILLER_LABEL = "FILLER";
    
    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    private static  String EXEC_TIMESTAMP_LABEL = "������";
    
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
     * (�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �A�b�v���[�h�t�@@�C���h�c<BR>
     */
    private String uploadFileId = "�O��������茋�ʈꊇ����";
    
    /**
     * (�O��������茋�ʈꊇ����CSV)<BR>
     * �R���X�g���N�^ <BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B <BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B <BR>
     * @@param l_lngUploadId - (�A�b�v���[�hID)<BR>
     * �A�b�v���[�hID<BR>
     * @@roseuid 429D5FD7033B
     */
    public WEB3FeqExecuteResultInputCSV(long l_lngUploadId) 
    {
        this.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (�O��������茋�ʈꊇ����CSV)<BR>
     * �R���X�g���N�^ <BR>
     * <BR>
     * �|this.create�J�����w�b�_()���R�[������B <BR>
     * @@roseuid 429D5FD5036A
     */
    public WEB3FeqExecuteResultInputCSV() 
    {
        this.createColumnHeader();     
    }
    
    /**
     * (create�J�����w�b�_ ())<BR>
     * �J�����w�b�_���Z�b�g����B <BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()<BR>
     * �ɂăC���X�^���X�ɃZ�b�g����B <BR>
     * <BR>
     * [�J�����w�b�_�z��] <BR>
     * <BR>
     * �|�@@index = 0�@@���^�p�R�[�h <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.�^�p�R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 0 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 1�@@�������R�[�h <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.�����R�[�h���x�� <BR>
     * �@@�J�����ԍ��F 1 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 2�@@������ <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.�������x�� <BR>
     * �@@�J�����ԍ��F 2 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������ <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 3�@@����芔��<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.��芔�����x�� <BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 4�@@�����P��<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.���P�����x�� <BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 5�@@�����No.<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���] <BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.���No.���x�� <BR>
     * �@@�J�����ԍ��F 5 <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�iint�j <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * �|�@@index = 6�@@��FILLER<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.FILLER���x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7�@@��������<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�O��������茋�ʈꊇ����CSV.���������x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t<BR>
     * �@@���t�t�H�[�}�b�g�F�@@yyyyMMddHHmmss<BR>
     * @@roseuid 429D62440260
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0�^�p�R�[�h
        l_models[0] = new WEB3GentradeCsvColumnModel(
            ORDER_EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1�����R�[�h
        l_models[1] = new WEB3GentradeCsvColumnModel(
            PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //index 2����
        l_models[2] = new WEB3GentradeCsvColumnModel(
            TRADE_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3��芔��
        l_models[3] = new WEB3GentradeCsvColumnModel(
            EXEC_QUANTITY_LABEL,
            3,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
            
        //index 4���P��
        l_models[4] = new WEB3GentradeCsvColumnModel(
            EXEC_PRICE_LABEL,
            4,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 5���No.
        l_models[5] = new WEB3GentradeCsvColumnModel(
            EXEC_NO_LABEL,
            5,
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null);
            
        //index 6FILLER
        l_models[6] = new WEB3GentradeCsvColumnModel(
            FILLER_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //index 7������
        l_models[7] = new WEB3GentradeCsvColumnModel(
            EXEC_TIMESTAMP_LABEL,
            7,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyyMMddHHmmss"));
            
        this.setColumnHeader(l_models);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * �A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * <BR>
     * �O��������茋�ʈꊇ����.�A�b�v���[�h�t�@@�C���h�c��ԋp����B<BR>
     * @@return String
     * @@roseuid 429D6A1C001E
     */
    public String getUploadFileId() 
    {
        return this.uploadFileId;
    }
    
    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v.�O��������ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 42C4E14401B8
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
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.�^�p�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429D6A5400AB
     */
    public String getOrderEmpCode(int l_intLineNumber) 
    {
        String l_strOrderEmpCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_EMP_CODE_LABEL));
        return l_strOrderEmpCode;
    }
    
    /**
     * (get�����R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖����R�[�h���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ����R�[�h���擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.�����R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429D6A9D030C
     */
    public String getProductCode(int l_intLineNumber) 
    {
        String l_strProductCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_CODE_LABEL));
        return l_strProductCode;
    }
    
    /**
     * (get����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̔������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĔ������擾���ԋp����B <BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.�������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@roseuid 429D6AB40389
     */
    public String getTrade(int l_intLineNumber) 
    {
        String l_strTrade = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(TRADE_LABEL));
        return l_strTrade;
    }
    
    /**
     * (get��芔��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖�芔�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ�芔�����擾���A�W���f�[�^�^�idouble�j<BR>
     * �ɕϊ����ĕԋp����B <BR>
     * �^�ϊ��Ɏ��s�����ꍇ�́A���l�ȊO���Z�b�g����Ă���Ɣ��f��<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02026<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.��芔�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 429D6ADE005C
     */
    public double getExecQuantity(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExecQuantity(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_QUANTITY_LABEL));
        if (!(l_obj instanceof Double))
        {
            String l_strMessage = "��萔�ʂ�9���ȓ��̐����l�ł͂���܂���B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return ((Double)l_obj).doubleValue();
    }
    
    /**
     * (get���P��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖��P�����擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ��P�����擾���A�W���f�[�^�^�idouble�j�ɕϊ����ĕԋp����B <BR>
     * �^�ϊ��Ɏ��s�����ꍇ�́A���l�ȊO���Z�b�g����Ă���Ɣ��f����O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02022<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.���P�����x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 429D6B0C0118
     */
    public double getExecPrice(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecPrice(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_PRICE_LABEL));
        if (!(l_obj instanceof Double))
        {
            String l_strMessage = "���P�������l�ȊO�̒l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        double l_dblExecPrice = ((Double)l_obj).doubleValue();

        log.exiting(STR_METHOD_NAME);     
        return l_dblExecPrice;
    }
    
    /**
     * (get���No.)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖��No.���擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ��No.���擾���A�W���f�[�^�^�iint�j�ɕϊ����ĕԋp����B <BR>
     * �^�ϊ��Ɏ��s�����ꍇ�́A���l�ȊO���Z�b�g����Ă���Ɣ��f����O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02191<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.���No.���x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 429D6B1D035A
     */
    public int getExecNo(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecNo(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = 
            this.getValue(l_intLineNumber, this.getColumnModel(EXEC_NO_LABEL));
        if (!(l_obj instanceof Integer))
        {
            String l_strMessage = "���No.�����l�ȊO�̒l�ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02191,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        int l_intExecNo = ((Integer)l_obj).intValue();
            
        log.exiting(STR_METHOD_NAME);
        return l_intExecNo;
    }
    
    /**
     * (get������)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̖��������擾����B <BR>
     * <BR>
     * this.get���ڒl()�ɂĖ��������擾���ADate�^�ɕϊ����ĕԋp����B <BR>
     * �^�ϊ��Ɏ��s�����ꍇ�́A�t�H�[�}�b�g�G���[�Ɣ��f����O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02192<BR>
     * ���������̃t�H�[�}�b�g�F�@@yyyyMMddHHmmss<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����] <BR>
     * �s�ԍ��F�@@�s�ԍ� <BR>
     * �J�����F�@@get�J�������f��(�O��������茋�ʈꊇ����CSV.���������x��)�̖߂�l�B<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 42AFED1600DA
     */
    public Date getExecTimestamp(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecTimestamp(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_TIMESTAMP_LABEL));
        if (l_obj != null && !(l_obj instanceof Date))
        {
            String l_strMessage = "�������̃t�H�[�}�b�g���s���ł��B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02192,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datExecTimestamp = (Date)l_obj;
        
        log.exiting(STR_METHOD_NAME);                
        return l_datExecTimestamp;
    }
    
    /**
     * (validate���׍s)<BR>
     * ���׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@�^�p�R�[�h�̃`�F�b�N <BR>
     * �@@get�^�p�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get�^�p�R�[�h()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�|�P�j�@@�^�p�R�[�h���擾�ł��Ȃ��ꍇ�iget�^�p�R�[�h() == null�j�A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02032<BR>
     *  <BR>
     * �@@�P�|�Q�j�@@�V���̔��p�p�����łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02033<BR>
     *  <BR>
     * �@@�P�|�R�j�@@��2byte������.�O�������^�p�R�[�h�̔ԋ敪�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_03164<BR>
     *  <BR>
     * �@@�P�|�S�j�@@�E5byte�������łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02035<BR>
     *  <BR>
     * �@@�P�|�T�j�@@this.get�����P��()�@@�ɂĊO�����������P�ʃI�u�W�F�N�g���擾����B<BR>
     * �@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02165<BR>
     *  <BR>
     * <BR>
     * �@@[get�����P��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�P�|�U�j�@@�����P�ʂ̃`�F�b�N<BR>
     * �@@�@@�@@�o���I����̒����̏ꍇ�i�P�|�T�j�Ŏ擾���������P��.is�o���I��() == true�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02144<BR>
     *  <BR>
     * �Q�j�@@�����̃`�F�b�N<BR>
     * �@@�Q�|�P�j�@@���擾<BR>
     * �@@�@@�����P��.getExecutions()�ɂāA�����Ɋ֘A��������擾����B<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�����ΏۊO�iSkip�f�[�^�j�̔���<BR>
     * �@@�@@�����ΏۊO�̖��׍s�̏ꍇ�A-1��ԋp���������I������B�ireturn -1;�j<BR>
     * <BR>
     * �@@�@@[�����ΏۊO�̔���]<BR>
     * �@@�@@getExecutions()�Ŏ擾�������ɁA�ȉ��̏����ɓ��Ă͂܂��肪���݂���ꍇ�B<BR>
     * <BR>
     * �@@�@@���.���ʔ� == get���No.(index) And<BR>
     * �@@�@@���.���P�� == get���P��(index) And<BR>
     * �@@�@@���.��芔�� == get����(index) And<BR>
     * �@@�@@���.���o�H�敪 == ���o�H�敪.2�F��茋�ʈꊇ����<BR>
     * <BR>
     * �@@�Q�|�R�j�@@���m���D�̃`�F�b�N<BR>
     * �@@�@@get���No.()�ɂāA�w��s�ԍ��̃f�[�^���擾����B<BR>
     * <BR>
     * �@@�@@[get���No.()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@get���No.()�ɂāA���No.���擾�ł����ꍇ�݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@�@@�Q�|�R�|�P�j�@@�擾�������No.���A�L������3���ȓ��̐��̐����l�łȂ��ꍇ�A��O<BR>
     * ���X���[����B
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02170<BR>
     *  <BR>
     * �@@�@@�Q�|�R�|�Q�j�@@�ʌo�H�œo�^���ꂽ���œ�����ʔԂ����ɑ��݂���ꍇ�i�G���[��<BR>
     * ���ɓ��Ă͂܂�ꍇ�j�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02171<BR>
     *  <BR>
     * �@@�@@[�G���[����]<BR>
     * �@@�@@getExecutions()�Ŏ擾�������ɁA�ȉ��̏����ɓ��Ă͂܂��肪���݂���ꍇ�B<BR>
     * �@@�@@<BR>
     * �@@�@@���.���ʔ� == get���No.(index)
     * <BR>
     * �@@�Q�|�S�j�@@�������̃`�F�b�N<BR>
     * �@@�@@get������()�ɂāA�w��s�ԍ��̃f�[�^���擾����B<BR>
     * �@@�@@���������擾�ł��Ȃ������ꍇ�iget������() == null�j�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02172<BR>
     *  <BR>
     * �@@�@@[get������()�Ɏw�肷�����]<BR>
     * �@@�@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�@@�O�����������}�l�[�W��.validate����()���R�[������B<BR>
     * <BR>
     * �@@�@@[validate����()�Ɏw�肷�����] <BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@�����F�@@get������()<BR>
     * <BR>
     * �R�j�@@�����R�[�h�̃`�F�b�N<BR>
     * �@@get�����R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�����R�[�h���擾�ł����ꍇ<BR>
     * �݈̂ȉ��̃`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get�����R�[�h()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ�<BR>
     * <BR>
     * �@@�R�|�P�j�@@��������9byte���傫���ꍇ�i�����R�[�h.length > 9�j�́A��O���X���[����B <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00439<BR>
     *  <BR>�@@
     * �R�|�Q�j�@@�����P�ʂ̖����ƈ�v���Ȃ��ꍇ�i�ȉ��̏����ɓ��Ă͂Ȃ�Ȃ��ꍇ�j�A<BR>
     * ��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00301<BR>
     *  <BR>
     * �@@[�����P�ʂ̖����ƈ�v�������]<BR>
     * �@@�����P��.getProduct().�����R�[�h == �����R�[�h�@@�܂��́A<BR>
     * �@@�����P��.getProduct().���n�����R�[�h == �����R�[�h<BR>
     * <BR>
     * �S�j�@@�����̃`�F�b�N<BR>
     * �@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�������擾�ł����ꍇ�݈̂ȉ���<BR>
     * �`�F�b�N���s���B<BR>
     * <BR>
     * �@@[get����()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@4�|�P�j�@@�������A�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01403<BR>
     *  <BR>�@@
     *  [�L���R�[�h�l]<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"S"�j�܂��́A<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"B"�j<BR>
     * <BR>
     * �@@4�|�Q�j�@@������ʁi�����P��.getOrderType()()�j�ƑΉ�����l�łȂ��ꍇ�A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02167<BR>
     *  <BR>
     * �@@[�Ή�����l]<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"B"�j�˒������.701�F�O������<BR>
     * �@@�O��������茋�ʈꊇ����CSV.����_���i"S"�j�˒������.702�F�O������<BR>
     * <BR>
     * �T�j�@@��芔���̃`�F�b�N<BR>
     * �@@get��芔��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get��芔��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     *  <BR>
     * �@@5�|�P�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02026<BR>
     *  <BR>
     * �@@�T�|�Q�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02186<BR>
     * <BR>
     * �U�j�@@���P���̃`�F�b�N<BR>
     * �@@get���P��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B <BR>
     * <BR>
     * �@@[get���P��()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�s�ԍ� <BR>
     * <BR>
     * �@@�U�|�P�j�@@get���P��()�̖߂�l <= 0�̏ꍇ�A��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02023<BR>
     *  <BR>
     * �@@�U�|�Q�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔͊O�ł���΁A<BR>
     * ��O���X���[����B<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02024<BR>
     *  <BR>
     * �@@�U�|�R�j�@@�O�����������}�l�[�W��.validate���P��()�ɂāA���P�����`�F�b�N����B<BR>
     * <BR>
     * �@@�@@[validate���P��()�Ɏw�肷�����]<BR>
     * �@@�@@�����P�ʁF�@@�����P��<BR>
     * �@@�@@��萔�ʁF�@@get���P��()<BR>
     * <BR>
     * �V�j�@@����I���i0�j��ԋp����B�ireturn 0;�j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@param l_strFeqOrderEmpCodeManageDiv - (�O�������^�p�R�[�h�̔ԋ敪)<BR>
     * �O�������^�p�R�[�h�̔ԋ敪<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 429D6C780260
     */
    public int validateDetailLine(int l_intLineNumber,
        String l_strFeqOrderEmpCodeManageDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailLine(int, String)";
        log.entering(STR_METHOD_NAME);

        //�P�j�@@�^�p�R�[�h�̃`�F�b�N 
        //�@@get�^�p�R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B 
        //�@@[get�^�p�R�[�h()�Ɏw�肷�����] 
        //�@@�s�ԍ��F�@@�s�ԍ�
        String l_strOrderEmpCode = this.getOrderEmpCode(l_intLineNumber); 
        
        //�@@�P�|�P�j�@@�^�p�R�[�h���擾�ł��Ȃ��ꍇ�iget�^�p�R�[�h() == null�j�A��O���X���[����B 
        if (WEB3StringTypeUtility.isEmpty(l_strOrderEmpCode))
        {
            String l_strMessage = "�^�p�R�[�h�������͂ł��B";
            log.debug(l_strMessage);
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�@@�P�|�Q�j�@@�V���̔��p�p�����łȂ��ꍇ�A��O���X���[����B
        final int MANAGEMENTCODE_LEN = 7;
        if (!WEB3StringTypeUtility.isLetterAndDigit(l_strOrderEmpCode) 
            || l_strOrderEmpCode.length() != MANAGEMENTCODE_LEN)
        {
            log.debug("�^�p�R�[�h = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02033,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h���V���̔��p�p�����ł͂���܂���B");
        }

        //�@@�P�|�R�j�@@��2byte������.�O�������^�p�R�[�h�̔ԋ敪�łȂ��ꍇ�A��O���X���[����B
        final int LEFT_LEN = 2;
        String l_strLeft2Byte = l_strOrderEmpCode.substring(0, LEFT_LEN);
        if (!l_strLeft2Byte.equals(l_strFeqOrderEmpCodeManageDiv))
        {
            log.debug("�^�p�R�[�h = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03164,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h�̍�2byte������.�O�������^�p�R�[�h�̔ԋ敪�ł͂���܂���B");
        }

        //�@@�P�|�S�j�@@�E5byte�������łȂ��ꍇ�A��O���X���[����B
        final int RIGHT_LEN = 5;
        String l_strRight5Byte = l_strOrderEmpCode.substring(
            l_strOrderEmpCode.length() - RIGHT_LEN);
        if (!WEB3StringTypeUtility.isDigit(l_strRight5Byte))
        {
            log.debug("�^�p�R�[�h = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02035,
                getClass().getName() + STR_METHOD_NAME,
                "�^�p�R�[�h�̉E5byte�������ł͂���܂���B");
        }

        //�@@�P�|�T�j�@@this.get�����P��()�@@�ɂĊO�����������P�ʃI�u�W�F�N�g���擾����B
        //�@@�@@�@@�@@�@@�@@�擾�ł��Ȃ������ꍇ�A��O���X���[����B
        WEB3FeqOrderUnit l_feqOrderUnit = this.getOrderUnit(l_intLineNumber);
        
        //�@@�P�|�U�j�@@�����P�ʂ̃`�F�b�N
        //�@@�@@�@@�o���I����̒����̏ꍇ�i�P�|�T�j�Ŏ擾���������P��.is�o���I��() == true�j�A
        //��O���X���[����B
        if (l_feqOrderUnit.isExecEnd())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                getClass().getName() + STR_METHOD_NAME,
                "�o���I�������ς݂Ȃ̂ŁA���s�ł��B");
        }
        
        //�Q�j�@@�����̃`�F�b�N
        //�@@�Q�|�P�j�@@���擾
        //�@@�@@�����P��.getExecutions()�ɂāA�����Ɋ֘A��������擾����B
        OrderExecution[] l_orderExecs = l_feqOrderUnit.getExecutions();
        
        //�@@�Q�|�Q�j�@@�����ΏۊO�iSkip�f�[�^�j�̔���
        //�@@�@@�����ΏۊO�̖��׍s�̏ꍇ�A-1��ԋp���������I������B�ireturn -1;�j
        //�@@�@@���.���ʔ� == get���No.(index) And
        //�@@�@@���.���P�� == get���P��(index) And
        //�@@�@@���.��芔�� == get����(index) And
        int l_intCount = 0;
        if (l_orderExecs != null)
        {
            l_intCount = l_orderExecs.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            OrderExecution l_orderExec = (OrderExecution)l_orderExecs[i];
            if (l_orderExec.getExecutionSerialNo() 
                    == this.getExecNo(l_intLineNumber)
                && l_orderExec.getExecutionPrice() 
                    == this.getExecPrice(l_intLineNumber)
                && l_orderExec.getExecutionQuantity() 
                    == this.getExecQuantity(l_intLineNumber))
            {
                return -1;
            }
        }
        
        //�@@�Q�|�R�j�@@���m���D�̃`�F�b�N
        //�@@�@@get���No.()�ɂāA�w��s�ԍ��̃f�[�^���擾����B
        int l_intExecNo = this.getExecNo(l_intLineNumber);

        //�@@�@@�Q�|�R�|�P�j�@@�擾�������No.���A�L������3���ȓ��̐��̐����l�łȂ��ꍇ�A��O���X���[����B
        if (l_intExecNo <= 0 || l_intExecNo >= 1000)
        {
            String l_strMessage = "���No�G���[�u" + l_intExecNo + "�v";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02170,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�@@�Q�|�S�j�@@�������̃`�F�b�N
        //�@@�@@get������()�ɂāA�w��s�ԍ��̃f�[�^���擾����B
        Date l_datExecDateTimestamp = this.getExecTimestamp(l_intLineNumber);
        
        //�@@�@@�O�����������}�l�[�W��.validate����()���R�[������B
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
        
        // ���������擾�ł����ꍇ
        // validate����()���R�[������B
        // [validate����()�Ɏw�肷�����] 
        //    �����P�ʁF�@@�����P�� 
        //    �����F�@@get������() 
        if (l_datExecDateTimestamp != null)
        {
            l_orderManager.validateExecutionDate(l_feqOrderUnit, l_datExecDateTimestamp);            
        }
        // ���������擾�ł��Ȃ������ꍇ
        else
        {
            // �J�������f���擾
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(EXEC_TIMESTAMP_LABEL);
            // �l�Z�b�g
            this.setValue(
                l_intLineNumber,
                l_model,
                GtlUtils.getSystemTimestamp());
        }
        //�R�j�@@�����R�[�h�̃`�F�b�N
        //�@@get�����R�[�h()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�����R�[�h���擾�ł����ꍇ
        //�݈̂ȉ��̃`�F�b�N���s���B
        String l_strProductCode = this.getProductCode(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strProductCode))
        {
            //�@@�R�|�P�j�@@��������9byte���傫���ꍇ�i�����R�[�h.length > 9�j�́A��O���X���[����B 
            //  class: WEB3BusinessLayerException
            //  tag:  BUSINESS_ERROR_00439
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
            
            //�R�|�Q�j�@@�����P�ʂ̖����ƈ�v���Ȃ��ꍇ�i�ȉ��̏����ɓ��Ă͂Ȃ�Ȃ��ꍇ�j�A
            //��O���X���[����B
            FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
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
        
        //�S�j�@@�����̃`�F�b�N
        //�@@get����()�ɂāA�w��s�ԍ��̃f�[�^���擾���A�������擾�ł����ꍇ�݈̂ȉ���
        //�`�F�b�N���s���B
        String l_strTrade = this.getTrade(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strTrade))
        {
            //�@@4�|�P�j�@@�������A�L���ȃR�[�h�l�łȂ��ꍇ�A��O���X���[����B
            // [�L���R�[�h�l]
            //�@@�O��������茋�ʈꊇ����CSV.����_���i"S"�j�܂��́A
            //�@@�O��������茋�ʈꊇ����CSV.����_���i"B"�j
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
            
            //�@@4�|�Q�j�@@������ʁi�����P��.getOrderType()()�j�ƑΉ�����l�łȂ��ꍇ�A
            //��O���X���[����B
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
        
        //�T�j�@@��芔���̃`�F�b�N
        //�@@get��芔��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B 
        double l_dblExecQuantity = this.getExecQuantity(l_intLineNumber);

        //�@@5�|�P�j�@@9���ȓ��̐����l�łȂ��ꍇ�A��O���X���[����B
        String l_strExecQuantity = l_dblExecQuantity + "";
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) 
            || WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            String l_strMessage = "��芔���G���[�u" + l_dblExecQuantity + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //�T�|�Q�j�@@���l�ɕϊ������l <= 0�̏ꍇ�A��O���X���[����B
        if (l_dblExecQuantity <= 0)
        {
            String l_strMessage = "��芔���G���[�u" + l_dblExecQuantity + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�U�j�@@���P���̃`�F�b�N
        //�@@get���P��()�ɂāA�w��s�ԍ��̃f�[�^���擾���`�F�b�N���s���B 
        double l_dblExecPrice = this.getExecPrice(l_intLineNumber);
        
        //�@@�U�|�P�jget���P��()�̖߂�l <= 0�̏ꍇ�A��O���X���[����B
        if (l_dblExecPrice <= 0)
        { 
            String l_strMessage = "���P�� <= 0�B�u" + l_dblExecPrice + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�@@�U�|�Q�j�@@���l�ɕϊ��������̗L���������A�������U���C�������T���͈̔͊O�ł���΁A
        //��O���X���[����B
        String l_strExecPrice = l_dblExecPrice + "";
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            String l_strMessage = "���P���G���[�u" + l_dblExecPrice + "�v";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //�@@�U�|�R�j�@@�O�����������}�l�[�W��.validate���P��()�ɂāA���P�����`�F�b�N����B
        l_orderManager.validateExecutedPrice(l_feqOrderUnit, l_dblExecPrice);
        
        //�V�j�@@����I���i0�j��ԋp����B�ireturn 0;�j
        return 0;
    }
    
    /**
     * (validate�d���s)<BR>
     * �ǉ��ς݂̖��׍s�̃`�F�b�N���s���B<BR>
     * <BR>
     * �P�j�@@get�^�p�R�[�h(�s�ԍ�)�Cget���No.(�s�ԍ�)�ɂāA�w��s�ԍ��̃f�[�^���擾����B<BR>
     * <BR>
     * �Q�j�@@���No.�d���`�F�b�N�C��芔���W�v<BR>
     * �@@��CSV�t�@@�C���ɒǉ��ς݂̖��׍s�i0�`�i�s�ԍ� -1�j�j�̐���LOOP<BR>
     * <BR>
     * �@@�Q�|�P�j�@@���No.�d���`�F�b�N<BR>
     * �@@�@@�w��s�ԍ��̉^�p�R�[�h�Ɩ��No.�Ɠ����l�̍s�i�^�p�R�[�h == �^�p�R�[�h && <BR>
     * ���No. == ���No.�j�����݂���ꍇ�A��O���X���[����B<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02173<BR>
     * �@@�Q�|�Q�j�@@��芔���`�F�b�N<BR>
     * �@@�@@�w��s�ԍ��̉^�p�R�[�h�Ɠ����^�p�R�[�h�̍s�ɂ��āA��芔�����W�v�isum�j����B<BR>
     * <BR>
     * �R�j�@@�W�v��̖�芔���ŁA��萔�ʃ`�F�b�N���s���B<BR>
     * �@@�O�����������}�l�[�W��.validate��萔��()���R�[������B<BR>
     * <BR>
     * �@@[validate��萔��()�Ɏw�肷�����] <BR>
     * �@@�����P�ʁF�@@�����P��<BR>
     * �@@��萔�ʁF�@@get��芔��(�s�ԍ�) + �Q�j�ŏW�v������芔���isum�j<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42C4CE38032F
     */
    public String validateRepeatLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRepeatLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@get�^�p�R�[�h(�s�ԍ�)�Cget���No.(�s�ԍ�)�ɂāA�w��s�ԍ��̃f�[�^���擾����B
        String l_strOrderEmpCode = this.getOrderEmpCode(l_intLineNumber);
        int l_intExecNo = this.getExecNo(l_intLineNumber);
        
        //�Q�j�@@���No.�d���`�F�b�N�C��芔���W�v
        //�@@�Q�|�P�j�@@���No.�d���`�F�b�N
        //�@@�@@�w��s�ԍ��̉^�p�R�[�h�Ɩ��No.�Ɠ����l�̍s�i�^�p�R�[�h == �^�p�R�[�h &&
        //�@@�@@���No. == ���No.�j�����݂���ꍇ�A��O���X���[����B
        //�@@�Q�|�Q�j�@@��芔���`�F�b�N
        //�@@�@@�w��s�ԍ��̉^�p�R�[�h�Ɠ����^�p�R�[�h�̍s�ɂ��āA��芔�����W�v�isum�j����B
        double l_dblExecQuantitySum = this.getExecQuantity(l_intLineNumber);
        for (int i = 0; i < l_intLineNumber; i++)
        {
            String l_strOrderEmpCodeTemp = this.getOrderEmpCode(i);
            int l_intExecNoTemp = this.getExecNo(i);
            if (l_strOrderEmpCode.equals(l_strOrderEmpCodeTemp))
            {
                if (l_intExecNo == l_intExecNoTemp)
                {
                    String l_strMessage = "�w��s�ԍ��̉^�p�R�[�h�����No.�Ɠ����l�̍s�����݂��܂��B";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02173,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }else{
					l_dblExecQuantitySum += this.getExecQuantity(i);
                }
            }
        }
        
        //�R�j�@@�W�v��̖�芔���ŁA��萔�ʃ`�F�b�N���s���B
        //�@@�O�����������}�l�[�W��.validate��萔��()���R�[������B        
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
        l_orderManager.validateExecutedQuantity(this.getOrderUnit(l_intLineNumber), l_dblExecQuantitySum);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get�����P��)<BR>
     * �^�p�R�[�h�ɊY�����钍���P�ʃI�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �O�����������}�l�[�W��.get�L�������P��By�^�p�R�[�h()�ɂāA�����P�ʃI�u�W�F�N�g���擾����B<BR>
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
     * @@roseuid 429D6C8C0176
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
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderEmpCode(
                l_datBizDate, 
                this.getOrderEmpCode(l_intLineNumber));//WEB3BaseException        
        
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "get�L�������P��By�^�p�R�[�h(" 
                + l_datBizDate 
                + ", " 
                + this.getOrderEmpCode(l_intLineNumber) 
                + ")��null�B";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (l_confirmedPrice)
        {
            //1.6.1 ��O���X���[����
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "������Ԃ�" + l_feqOrderUnit.getOrderStatus() + "�ł�");
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnit;
    }
}
@
