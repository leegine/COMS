head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.19;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PLSProfitLossSpecsCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���v����CSV(WEB3PLSProfitLossSpecsCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/10/20 ���� (���u) �V�K�쐬
                 : 2006/11/07  ���� (���u) ���f��067
*/
package webbroker3.tradehistory;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApplicationCodeDef;
import webbroker3.common.define.WEB3CommodityCodeTypeDef;
import webbroker3.common.define.WEB3ProfitLossRecordDivDef;
import webbroker3.common.define.WEB3ProfitLossRemarkDef;
import webbroker3.common.define.WEB3ReturnDivDef;
import webbroker3.common.define.WEB3TermDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.data.CodeTranslationRow;
import webbroker3.tradehistory.define.WEB3PLSProfitLossSpecsWkCodeDef;
import webbroker3.tradehistory.define.WEB3PlsBvsCarryoverBalanceRecDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (���v����CSV)<BR>
 * ���v����CSV
 * 
 * @@author ����
 * @@version 1.0
 */
public class WEB3PLSProfitLossSpecsCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O���[�e�B���e�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3PLSProfitLossSpecsCsv.class);
    
    /**
     * (�v�Z�����x��)<BR>
     * �萔��`�v���p�e�B�@@"�v�Z��"<BR>
     */
    private static String CALCDATE_LABEL = "�v�Z��";
    
    /**
     * (��n�����x��)<BR>
     * �萔��`�v���p�e�B�@@"��n��"<BR>
     */
    private static String DELIVERYDATE_LABEL = "��n��";
    
    /**
     * (�i���i�j�K�p���x��)<BR>
     * �萔��`�v���p�e�B�@@"�i���i�j�K�p"<BR>
     */
    private static String FUNDTYPEAPP_LABEL = "(���i)�K�p";
    
    /**
     * (���������x��)<BR>
     * �萔��`�v���p�e�B�@@"������"<BR>
     */
    private static String PRODUCTNAME_LABEL = "������";
    
    /**
     * (���Z���x��)<BR>
     * �萔��`�v���p�e�B�@@"���Z"<BR>
     */
    private static String TERM_LABEL = "���Z";
    
    /**
     * (���ʃ��x��)<BR>
     * �萔��`�v���p�e�B�@@"����"<BR>
     */
    private static String QUANTITY_LABEL = "����";
    
    /**
     * (���n�����x��)<BR>
     * �萔��`�v���p�e�B�@@"���n��"<BR>
     */
    private static String PASSDATE_LABEL = "���n��";
    
    /**
     * (���n���z���x��)<BR>
     * �萔��`�v���p�e�B�@@"���n���z"<BR>
     */
    private static String PASSAMOUNT_LABEL = "���n���z";
    
    /**
     * (�擾�����x��)<BR>
     * �萔��`�v���p�e�B�@@"�擾��"<BR>
     */
    private static String GETDATE_LABEL = "�擾��";
    
    /**
     * (�擾����x��)<BR>
     * �萔��`�v���p�e�B�@@"�擾�"<BR>
     */
    private static String GETAMOUNT_LABEL = "�擾�";
    
    /**
     * (���v���x��)<BR>
     * �萔��`�v���p�e�B�@@"���v"<BR>
     */
    private static String PROLOSSAMOUNT_LABEL = "���v";
    
    /**
     * (�݌v���v���x��)<BR>
     * �萔��`�v���p�e�B�@@"�݌v���v"<BR>
     */
    private static String TOTALPROLOSSAMOUNT_LABEL = "�݌v���v";
    
    /**
     * (�ېőΏۊz���x��)<BR>
     * �萔��`�v���p�e�B�@@"�ېőΏۊz"<BR>
     */
    private static String TAXABLEAMOUNT_LABEL = "�ېőΏۊz";
    
    /**
     * (�����Ŋz���x��)<BR>
     * �萔��`�v���p�e�B�@@"�����Ŋz"<BR>
     */
    private static String COLLECTTAXAMOUNT_LABEL = "�����Ŋz";
    
    /**
     * (�����Ŋz�i���Łj���x��)<BR>
     * �萔��`�v���p�e�B�@@"�����Ŋz�i���Łj"<BR>
     */
    private static String COLLECTTAXNAMOUNT_LABEL = "�����Ŋz(����)";
    
    /**
     * (�����Ŋz�i�n���Łj���x��)<BR>
     * �萔��`�v���p�e�B�@@"�����Ŋz�i�n���Łj"<BR>
     */
    private static String COLLECTTAXLAMOUNT_LABEL = "�����Ŋz(�n����)";
    
    /**
     * (���v����CSV)<BR>
     * �R���X�g���N�^�B  <BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B  <BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B  <BR>
     * �@@�|create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B  <BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     */
    public WEB3PLSProfitLossSpecsCsv()
    {
        //super()�ɂăC���X�^���X�𐶐�����B
        super();
        
        //create�L�[�w�b�_()���R�[�����A�L�[�w�b�_�����쐬����B 
        this.createKeyHeader();
        
        //create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B  
        this.createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B  <BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B  <BR>
     * <BR>
     * [�J�����w�b�_�z��]  <BR>
     * <BR>
     * �|�@@index = 0  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�v�Z�����x��  <BR>
     * �@@�J�����ԍ��F 0  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 1  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.��n�����x��  <BR>
     * �@@�J�����ԍ��F 1  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * �|�@@index = 2  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.(���i)�K�p���x��  <BR>
     * �@@�J�����ԍ��F 2  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 3  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���������x��  <BR>
     * �@@�J�����ԍ��F 3  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 4  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���Z���x��  <BR>
     * �@@�J�����ԍ��F 4  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 5  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���ʃ��x��  <BR>
     * �@@�J�����ԍ��F 5  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 6  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���n�����x��  <BR>
     * �@@�J�����ԍ��F 6  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * �|�@@index = 7  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���n���z���x��  <BR>
     * �@@�J�����ԍ��F 7  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 8  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�擾�����x��  <BR>
     * �@@�J�����ԍ��F 8  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t�^  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@new SimpleDateFormat("yyyy/MM/dd")  <BR>
     * <BR>
     * �|�@@index = 9  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�擾����x��  <BR>
     * �@@�J�����ԍ��F 9  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 10  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.���v���x��  <BR>
     * �@@�J�����ԍ��F 10  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 11  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�݌v���v���x��  <BR>
     * �@@�J�����ԍ��F 11  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null  <BR>
     * <BR>
     * �|�@@index = 12  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�ېőΏۊz���x��  <BR>
     * �@@�J�����ԍ��F 12  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 13  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz���x��  <BR>
     * �@@�J�����ԍ��F 13  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 14  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz(����)���x��  <BR>
     * �@@�J�����ԍ��F 14  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     * <BR>
     * �|�@@index = 15  <BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]  <BR>
     * �@@���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz(�n����)���x��  <BR>
     * �@@�J�����ԍ��F 15  <BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������  <BR>
     * �@@���t�t�H�[�}�b�g�F�@@null <BR>
     */
    public void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        final int COLUMN_MAX = 16;
        
        WEB3GentradeCsvColumnModel[] l_columnModel = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�v�Z�����x��
        l_columnModel[0] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.CALCDATE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.��n�����x��
        l_columnModel[1] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.DELIVERYDATE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV..(���i)�K�p���x��  
        l_columnModel[2] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.FUNDTYPEAPP_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���������x��  
        l_columnModel[3] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PRODUCTNAME_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���Z���x��  
        l_columnModel[4] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TERM_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���ʃ��x��  
        l_columnModel[5] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.QUANTITY_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���n�����x��
        l_columnModel[6] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PASSDATE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���n���z���x��  
        l_columnModel[7] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PASSAMOUNT_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�擾�����x��  
        l_columnModel[8] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.GETDATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�擾����x�� 
        l_columnModel[9] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.GETAMOUNT_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.���v���x��  
        l_columnModel[10] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.PROLOSSAMOUNT_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�݌v���v���x��  
        l_columnModel[11] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TOTALPROLOSSAMOUNT_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�ېőΏۊz���x��  
        l_columnModel[12] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.TAXABLEAMOUNT_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz���x��  
        l_columnModel[13] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXAMOUNT_LABEL,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz(����)���x��  
        l_columnModel[14] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXNAMOUNT_LABEL,
            14,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //���ڃ��x���F�@@���v���׏Ɖ�CSV.�����Ŋz(�n����)���x��  
        l_columnModel[15] = new WEB3GentradeCsvColumnModel(
            WEB3PLSProfitLossSpecsCsv.COLLECTTAXLAMOUNT_LABEL,
            15,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        log.exiting(STR_METHOD_NAME);
        this.setColumnHeader(l_columnModel);
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>  
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����Aset�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B  <BR>
     * <BR>
     * [�L�[�w�b�_�z��]  <BR>
     * <BR>
     * �|�@@index = 0  <BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B  <BR>
     * <BR>
     * (*1) ���ݓ���  <BR>
     * TradingSystem.getSystemTimestamp()<BR>
     */
    public void createKeyHeader()
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeaders = new String[1];
        
        //���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B   
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        //set�L�[�w�b�_
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�v�Z��)<BR>
     * �u�v�Z���v���Z�b�g����B<BR>  
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�v�Z�����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�v�Z�� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strCalcDate - (�v�Z��)<BR>
     * �v�Z��
     */
    public void setCalcDate(int l_intLineNumber, String l_strCalcDate)
    {
        final String STR_METHOD_NAME = " setCalcDate(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�v�Z�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(CALCDATE_LABEL);
        
        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�v�Z�� 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCalcDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��n��)<BR>
     * �u��n���v���Z�b�g����B  <BR>
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.��n�����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.��n�� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_datDeliveryDate - (��n��)<BR>
     * ��n��
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = " setDeliveryDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.��n�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(DELIVERYDATE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.��n�� 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datDeliveryDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�i���i�j�K�p)<BR>
     * �u(���i)�K�p�v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@���v����CSV.�i���i�j�K�p���x��  <BR>
     * <BR>
     * <BR>
     * �Q�j���������l�̐ݒ�  <BR>
     * <BR>
     * �@@[wk�ϐ���`] <BR>
     * �@@�@@�Ewk�R�[�h <BR>
     * <BR>
     * �@@�i�P�jwk�R�[�h�ݒ� <BR>
     * �@@�@@�@@�@@�p�����[�^.���i�R�[�h = 10:���������̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"1000"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�A�p�����[�^.���i�R�[�h = 11:�~�j�������̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"1100"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�B�p�����[�^.���i�R�[�h = 12:�~�j�������̏ꍇ <BR>
     * �@@�@@�@@�@@A. �p�����[�^.�K�p�R�[�h = 21:�~�j�[�������̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1221"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@B. �p�����[�^.�K�p�R�[�h = 22:�~�j�L�������̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1222"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@C. �p�����[�^.�K�p�R�[�h = 21�E22�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1200"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�C�p�����[�^.���i�R�[�h = 15:�M�p����̏ꍇ <BR>
     * �@@�@@�@@�@@A. �p�����[�^.�K�p�R�[�h = 11:�m��z���̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1511"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@B. �p�����[�^.�K�p�R�[�h = 12:�a��z���̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1512"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@C. �p�����[�^.�K�p�R�[�h = 13:�����󕥋��̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1513"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@D. �p�����[�^.�K�p�R�[�h = 11�E12�E13�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"1500"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�D�p�����[�^.���i�R�[�h = 20:���M����̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"2000"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�E�p�����[�^.���i�R�[�h = 21:���M����̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"2100"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�F�p�����[�^.���i�R�[�h = 30:�������̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"3000"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�G�p�����[�^.���i�R�[�h = 40:�O�������̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"4000"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�H�p�����[�^.���i�R�[�h = 42:�O�������̏ꍇ <BR>
     * �@@�@@�@@�@@A. �p�����[�^.�K�p�R�[�h = 31:�O���������p�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"4231"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@B. �p�����[�^.�K�p�R�[�h = 31�ȊO�̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"4200"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�I�p�����[�^.���i�R�[�h����L�ȊO�̏ꍇ�inull���܂ށj <BR>
     * �@@�@@�@@�@@�@@- �S�j�l�Z�b�g ���s�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@�ɂ�null��ݒ肷�� <BR>
     * <BR>
     * <BR>
     * �R�j�\�����b�Z�[�W�̎擾  <BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()���R�[������B  <BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@arg0�F�@@�R�[�h�|��e�[�u��(code_translation)  <BR>
     * �@@arg1�F�@@�I�v�V���i��������(*1�j  <BR>
     * �@@arg2�F�@@�I�u�W�F�N�g�z��i*2�j  <BR>
     * <BR>
     * �@@���擾�ł��Ȃ��ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �@@�i*1�j�I�v�V���i��������  <BR>
     * <BR>
     * �@@�@@�@@�ȉ��̕�������쐬����B  <BR>
     * �@@�@@�@@�@@"code_type = 'pls_comdv' "  <BR>
     * �@@�@@�@@�{" and institution_code = ? "  <BR>
     * �@@�@@�@@�{" and code = ?"  <BR>
     * <BR>
     * �@@�i*2�j�I�u�W�F�N�g�z��  <BR>
     * <BR>
     * �@@�@@�@@�ȉ��̏���ArrayList���쐬����B  <BR>
     * �@@�@@�@@�E�p�����[�^.��ЃR�[�h  <BR>
     * �@@�@@�@@�Ewk�R�[�h  <BR>
     * <BR>
     * <BR>
     * �S�j�l�Z�b�g  <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B <BR> 
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�l�F�@@�R�j�Ŏ擾�����A�R�[�h�|��e�[�u��.�\�����b�Z�[�W <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strFundType - (���i)<BR>
     * ���i
     * @@param l_strApplicationCode - (�K�p�R�[�h)<BR>
     * �K�p�R�[�h
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h
     * @@throws WEB3BaseException 
     */
    public void setFundTypeApplication(
        int l_intLineNumber, String l_strFundType, String l_strApplicationCode, String l_strInstitutionCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setFundTypeApplication(int, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�i���i�j�K�p���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(FUNDTYPEAPP_LABEL);
        
        //�Q�j���������l�̐ݒ�
        //�@@[wk�ϐ���`] 
        //�@@�Ewk�R�[�h
        String l_strWkCode = "";
        
        //�i�P�jwk�R�[�h�ݒ� 
        //�@@�p�����[�^.���i�R�[�h = 10:���������̏ꍇ 
        //- wk�R�[�h��"1000"���Z�b�g����  
        //�A�p�����[�^.���i�R�[�h = 11:�~�j�������̏ꍇ 
        //- wk�R�[�h��"1100"���Z�b�g����  
        //�B�p�����[�^.���i�R�[�h = 12:�~�j�������̏ꍇ 
        //A. �p�����[�^.�K�p�R�[�h = 21:�~�j�[�������̏ꍇ 
        //- wk�R�[�h��"1221"���Z�b�g����   
        //B. �p�����[�^.�K�p�R�[�h = 22:�~�j�L�������̏ꍇ 
        //- wk�R�[�h��"1222"���Z�b�g����   
        //C. �p�����[�^.�K�p�R�[�h = 21�E22�ȊO�̏ꍇ 
        //- wk�R�[�h��"1200"���Z�b�g���� 
        if (WEB3CommodityCodeTypeDef.JAPANESE_STOCK_TRADE.equals(l_strFundType))
        {
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1000;
        }
        else if (WEB3CommodityCodeTypeDef.MINI_STOCK_TRADE.equals(l_strFundType))
        {
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1100;;
        }
        else if (WEB3CommodityCodeTypeDef.MINI_STOCK_CLAIM.equals(l_strFundType))
        {
            if (WEB3ApplicationCodeDef.MINI_STOCK_SALE.equals(l_strApplicationCode))
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1221;
            }
            else if (WEB3ApplicationCodeDef.MINI_STOCK_ONEROUS_INCREASE.equals(l_strApplicationCode))
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1222;
            }
            else
            {
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1200;               
            }
        }
        
        //�C�p�����[�^.���i�R�[�h = 15:�M�p����̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.MARGIN.equals(l_strFundType))
        {
            //A. �p�����[�^.�K�p�R�[�h = 11:�m��z���̏ꍇ 
            if (WEB3ApplicationCodeDef.CONFIRM_DIVIDEND.equals(l_strApplicationCode))
            {
                //- wk�R�[�h��"1511"���Z�b�g����   
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1511;
            }
            //B. �p�����[�^.�K�p�R�[�h = 12:�a��z���̏ꍇ 
            else if (WEB3ApplicationCodeDef.DEPOSIT_DIVIDEND.equals(l_strApplicationCode))
            {
                //- wk�R�[�h��"1512"���Z�b�g����
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1512;
            }
            //C. �p�����[�^.�K�p�R�[�h = 13:�����󕥋��̏ꍇ 
            else if (WEB3ApplicationCodeDef.CLAIM_PAYMENT.equals(l_strApplicationCode))
            {
                //- wk�R�[�h��"1513"���Z�b�g����  
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1513;
            }
            //D. �p�����[�^.�K�p�R�[�h = 11�E12�E13�ȊO�̏ꍇ 
            else 
            {
                //- wk�R�[�h��"1500"���Z�b�g����
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_1500;
            }
        }
        
        //�D�p�����[�^.���i�R�[�h = 20:���M����̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADE.equals(l_strFundType))
        {
            //- wk�R�[�h��"2000"���Z�b�g����
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_2000;
        }
        
        //�E�p�����[�^.���i�R�[�h = 21:���M����̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.MUTUAL_FUND_TRADING.equals(l_strFundType))
        {
            //- wk�R�[�h��"2100"���Z�b�g����  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_2100;
        }

        //�F�p�����[�^.���i�R�[�h = 30:�������̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.JAPANESE_BOND_TRADE.equals(l_strFundType))
        {
            //- wk�R�[�h��"3000"���Z�b�g����  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_3000;
        }
        
        //�G�p�����[�^.���i�R�[�h = 40:�O�������̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.FOREIGN_STOCK_TRADE.equals(l_strFundType))
        {
            //- wk�R�[�h��"4000"���Z�b�g����  
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4000;
        }
        
        //�H�p�����[�^.���i�R�[�h = 42:�O�������̏ꍇ 
        else if (WEB3CommodityCodeTypeDef.FOREIGN_STOCK_CLAIM.equals(l_strFundType))
        {
            //A. �p�����[�^.�K�p�R�[�h = 31:�O���������p�̏ꍇ 
            if (WEB3ApplicationCodeDef.FOREIGN_STOCK_CLAIM_SALE.equals(l_strApplicationCode))
            {
                //- wk�R�[�h��"4231"���Z�b�g����
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4231;
            }
            //B. �p�����[�^.�K�p�R�[�h = 31�ȊO�̏ꍇ 
            else
            {
                //- wk�R�[�h��"4200"���Z�b�g����
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_COMDV_4200;
            }
        }

        //�I�p�����[�^.���i�R�[�h����L�ȊO�̏ꍇ�inull���܂ށj 
        //- �S�j�l�Z�b�g ���s�� 
        //�l�F�@@�ɂ�null��ݒ肷��
        else 
        {
            l_strWkCode = null;
        }
        
        //�R�j�\�����b�Z�[�W�̎擾  
        //QueryProcessor.doFindAllQuery()���R�[������B  
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^]  
        //arg0�F�@@�R�[�h�|��e�[�u��(code_translation)  
        //arg1�F�@@�I�v�V���i��������(*1�j  
        //�i*2�j�I�u�W�F�N�g�z��  
        //�ȉ��̏���ArrayList���쐬����B  
        //�E�p�����[�^.��ЃR�[�h  
        //�Ewk�R�[�h 
        //arg2�F�@@�I�u�W�F�N�g�z��i*2�j  
        //���擾�ł��Ȃ��ꍇ�Anull��ԋp����B 
        List l_lisRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("code_type = 'pls_comdv' ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and code = ?");
        Object[] l_objWhere = 
            new Object[]{l_strInstitutionCode, l_strWkCode};
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }
        String l_strDisplayMessage = "";
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            l_strDisplayMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strDisplayMessage = l_translationRow.getDisplayMessage();
        }
        //�S�j�l�Z�b�g  
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B  
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�R�j�Ŏ擾�����A�R�[�h�|��e�[�u��.�\�����b�Z�[�W 
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strDisplayMessage);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set������)<BR>
     * �u�������v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾<BR>  
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>  
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>  
     * �@@���ڃ��x���F�@@���v����CSV.���������x��<BR>  
     * <BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>  
     * ���p�����[�^.���R�[�h�敪 = 20:���׃��R�[�h�̏ꍇ�Ɏ��{����<BR> 
     * <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>  
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]<BR>  
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>  
     * �@@�@@�l�F�@@�p�����[�^.������  <BR>
     * <BR>
     * <BR>
     * �R�j�\�����b�Z�[�W���擾���l�Z�b�g<BR> 
     * ���p�����[�^.���R�[�h�敪 = 20:���׃��R�[�h�ȊO�̏ꍇ�Ɏ��{����<BR> 
     * <BR>
     * �@@[wk�ϐ���`]<BR> 
     * �@@�@@�Ewk�R�[�h <BR>
     * <BR>
     * �@@�i�P�jwk�R�[�h�ݒ�<BR> 
     * �@@�@@�@@�@@�p�����[�^.���R�[�h�敪 = 00:�J�z�c�����R�[�h�̏ꍇ<BR> 
     * �@@�@@�@@�@@- wk�R�[�h��"90"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�A�p�����[�^.���R�[�h�敪 = 10:�c�����R�[�h�̏ꍇ<BR> 
     * �@@�@@�@@�@@- wk�R�[�h��"91"���Z�b�g����  <BR>
     * <BR>
     * �@@�@@�@@�B�p�����[�^.���R�[�h�敪 = 21:���o�����R�[�h�̏ꍇ<BR> 
     * �@@�@@�@@�@@A. �p�����[�^.�Ԋҋ��敪 = 1:�Ԋҋ��̏ꍇ <BR>
     * �@@�@@�@@�@@�@@A1. �p�����[�^.���l = 1:�v�Z�ς̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@- wk�R�[�h��"11"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@�@@A2. �p�����[�^.���l = 1�ȊO�̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@- wk�R�[�h��"10"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@B. �p�����[�^.�Ԋҋ��敪 = 1�ȊO�̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@B1. �p�����[�^.���l = 1:�v�Z�ς̏ꍇ <BR>
     * �@@�@@�@@�@@�@@�@@- wk�R�[�h��"01"���Z�b�g����   <BR>
     * <BR>
     * �@@�@@�@@�@@�@@B2. �p�����[�^.���l = 1�ȊO�̏ꍇ<BR> 
     * �@@�@@�@@�@@�@@�@@- wk�R�[�h��"00"���Z�b�g����   <BR>
     * <BR>
     * �@@�i�Q�j�\�����b�Z�[�W�̎擾<BR>  
     * <BR>
     * �@@�@@QueryProcessor.doFindAllQuery()���R�[������B<BR>  
     * <BR>
     * �@@�@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]<BR>  
     * �@@�@@arg0�F�@@�R�[�h�|��e�[�u��(code_translation) <BR> 
     * �@@�@@arg1�F�@@�I�v�V���i��������(*1�j  <BR>
     * �@@�@@arg2�F�@@�I�u�W�F�N�g�z��i*2�j  <BR>
     * <BR>
     * �@@�@@���擾�ł��Ȃ��ꍇ�Anull��ԋp����B<BR> 
     * <BR>
     * �@@�@@�i*1�j�I�v�V���i��������<BR>  
     * <BR>
     * �@@�@@�@@�@@�ȉ��̕�������쐬����B<BR>  
     * �@@�@@�@@�@@�@@"code_type = 'pls_retdv' "  <BR>
     * �@@�@@�@@�@@�{" and institution_code = ? "  <BR>
     * �@@�@@�@@�@@�{" and code = ?"  <BR>
     * <BR>
     * �@@�@@�i*2�j�I�u�W�F�N�g�z��<BR>  
     * <BR>
     * �@@�@@�@@�@@�ȉ��̏���ArrayList���쐬����B<BR>  
     * �@@�@@�@@�@@�E�p�����[�^.��ЃR�[�h  <BR>
     * �@@�@@�@@�@@�Ewk�R�[�h  <BR>
     * <BR>
     * �@@�i�R�j�l�Z�b�g<BR>  
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>  
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]<BR>  
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>  
     * �@@�@@�l�F�@@�i�Q�j�Ŏ擾�����A�R�[�h�|��e�[�u��.�\�����b�Z�[�W<BR> 
     * @@param l_strLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strRecordDiv - (���R�[�h�敪)<BR>
     * ���R�[�h�敪
     * @@param l_strReturnDiv - (�Ԋҋ��敪)<BR>
     * �Ԋҋ��敪
     * @@param l_strRemark - (���l)<BR>
     * ���l
     * @@param l_strProductName - (������)<BR>
     * ������
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h
     * @@throws WEB3BaseException 
     */
    public void setProductName(
        String l_strLineNumber, String l_strRecordDiv, 
        String l_strReturnDiv, String l_strRemark, 
        String l_strProductName, String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME =
            " setProductName(String, String, String, String, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���������x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PRODUCTNAME_LABEL);
        
        //�Q�j�l�Z�b�g  
        //���p�����[�^.���R�[�h�敪 = 20:���׃��R�[�h�̏ꍇ�Ɏ��{���� 
        //�@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  
        //�@@�@@[set���ڒl()�Ɏw�肷�����]  
        //�@@�@@�s�ԍ��F�@@�����̍s�ԍ�  
        //�@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�@@�@@�l�F�@@�p�����[�^.������          
        if (WEB3ProfitLossRecordDivDef.DETAIL_REC.equals(l_strRecordDiv))
        {
            this.setValue(
                Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strProductName);
            log.exiting(STR_METHOD_NAME);
        }
        
        //�R�j�\�����b�Z�[�W���擾���l�Z�b�g 
        //���p�����[�^.���R�[�h�敪 = 20:���׃��R�[�h�ȊO�̏ꍇ�Ɏ��{���� 
        else
        {
            //�@@[wk�ϐ���`] 
            //�@@�@@�Ewk�R�[�h 
            String l_strWkCode = "";
            //�@@�i�P�jwk�R�[�h�ݒ� 
            //�@@�@@�@@�@@�p�����[�^.���R�[�h�敪 = 00:�J�z�c�����R�[�h�̏ꍇ 
            if (WEB3PlsBvsCarryoverBalanceRecDef.CARRYOVER_BALANCE_REC.equals(l_strRecordDiv))
            {
                //�@@�@@�@@�@@- wk�R�[�h��"90"���Z�b�g����  
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_90;
            }
            //�@@�@@�@@�A�p�����[�^.���R�[�h�敪 = 10:�c�����R�[�h�̏ꍇ 
            else if (WEB3ProfitLossRecordDivDef.BALANCE_REC.equals(l_strRecordDiv))
            {
                //�@@�@@�@@�@@- wk�R�[�h��"91"���Z�b�g����   
                l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_91;
            }
            //�@@�@@�@@�B�p�����[�^.���R�[�h�敪 = 21:���o�����R�[�h�̏ꍇ 
            else if (WEB3ProfitLossRecordDivDef.ORDER_REC.equals(l_strRecordDiv))
            {
                //�@@�@@�@@�@@A. �p�����[�^.�Ԋҋ��敪 = 1:�Ԋҋ��̏ꍇ 
                if (WEB3ReturnDivDef.RETURN.equals(l_strReturnDiv))
                {
                    //�@@�@@�@@�@@�@@A1. �p�����[�^.���l = 1:�v�Z�ς̏ꍇ 
                    if (WEB3ProfitLossRemarkDef.CALCULATED.equals(l_strRemark))
                    {
                        //�@@�@@�@@�@@�@@�@@- wk�R�[�h��"11"���Z�b�g����
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_11;
                    }
                    //�@@�@@�@@�@@�@@A2. �p�����[�^.���l = 1�ȊO�̏ꍇ 
                    else 
                    {
                        //�@@�@@�@@�@@�@@�@@- wk�R�[�h��"10"���Z�b�g����   
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_10;
                    }
                }
                //�@@�@@�@@�@@B. �p�����[�^.�Ԋҋ��敪 = 1�ȊO�̏ꍇ 
                else
                {
                    //�@@�@@�@@�@@�@@B1. �p�����[�^.���l = 1:�v�Z�ς̏ꍇ 
                    if (WEB3ProfitLossRemarkDef.CALCULATED.equals(l_strRemark))
                    {
                        //�@@�@@�@@�@@�@@�@@- wk�R�[�h��"01"���Z�b�g����   
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_01;
                    }
                    //�@@�@@�@@�@@�@@B2. �p�����[�^.���l = 1�ȊO�̏ꍇ 
                    else 
                    {
                        //�@@�@@�@@�@@�@@�@@- wk�R�[�h��"00"���Z�b�g���� 
                        l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_RETDV_00;
                    }
                }
            }
            //�i�Q�j�\�����b�Z�[�W�̎擾  
            //QueryProcessor.doFindAllQuery()���R�[������B  
            //[doFindAllQuery()�ɃZ�b�g����p�����[�^]  
            //arg0�F�@@�R�[�h�|��e�[�u��(code_translation)  
            //arg1�F�@@�I�v�V���i��������(*1�j  
            //arg2�F�@@�I�u�W�F�N�g�z��i*2�j  
            //���擾�ł��Ȃ��ꍇ�Anull��ԋp����B 
            //�i*1�j�I�v�V���i��������  
            //�ȉ��̕�������쐬����B  
            //"code_type = 'pls_retdv' "  
            //�{" and institution_code = ? "  
            //�{" and code = ?"  
            //�i*2�j�I�u�W�F�N�g�z��  
            //�ȉ��̏���ArrayList���쐬����B  
            //�E�p�����[�^.��ЃR�[�h
            //�Ewk�R�[�h  
            List l_lisRecords = new ArrayList();
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("code_type = 'pls_retdv' ");
            l_sbWhere.append("and institution_code = ? ");
            l_sbWhere.append("and code = ?");
            
            Object[] l_objWhere = 
                new Object[]{l_strInstitutionCode, l_strWkCode};
            try
            {
                QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
                l_lisRecords = l_QueryProcessor.doFindAllQuery(
                    CodeTranslationRow.TYPE,
                    l_sbWhere.toString(),
                    null,
                    l_objWhere);
            }
            catch (DataFindException l_dfe)
            {
                log.error(STR_METHOD_NAME, l_dfe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dfe.getMessage(),
                    l_dfe);
            }
            catch (DataQueryException l_dqe)
            {
                log.error(STR_METHOD_NAME, l_dqe);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dqe.getMessage(),
                    l_dqe);
            }
            catch (DataNetworkException l_dne)
            {
                log.error(STR_METHOD_NAME, l_dne);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_dne.getMessage(),
                    l_dne);
            }

            String l_strDisplayMessage = "";
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                l_strDisplayMessage = null;
            }
            else
            {
                CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
                l_strDisplayMessage = l_translationRow.getDisplayMessage();
            }
            //�i�R�j�l�Z�b�g  
            //this.set���ڒl()�ɂč��ڒl���Z�b�g����B  
            //[set���ڒl()�Ɏw�肷�����]  
            //�s�ԍ��F�@@�����̍s�ԍ�  
            //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
            //�l�F�@@�i�Q�j�Ŏ擾�����A�R�[�h�|��e�[�u��.�\�����b�Z�[�W 
            this.setValue(Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strDisplayMessage);
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    /**
     * (set���Z)<BR>
     * �u���Z�v���Z�b�g����B<BR>  
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@���ڃ��x���F�@@���v����CSV.���Z���x��  <BR>
     * <BR>
     * <BR>
     * �Q�j���������l�̐ݒ�  <BR>
     * <BR>
     * �@@[wk�ϐ���`] <BR>
     * �@@�@@�Ewk�R�[�h <BR>
     * <BR>
     * �@@�i�P�jwk�R�[�h�ݒ� <BR>
     * �@@�@@�@@�@@�p�����[�^.���Z���敪 = 1:��ʏ��̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"1"���Z�b�g���� <BR>
     * <BR>
     * �@@�@@�@@�A�p�����[�^.���Z���敪 = 2:����M�p�̏ꍇ <BR>
     * �@@�@@�@@�@@- wk�R�[�h��"2"���Z�b�g���� <BR>
     * <BR>
     * �@@�@@�@@�B�p�����[�^.���Z���敪 = 3:�������̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"3"���Z�b�g���� <BR>
     * <BR>
     * �@@�@@�@@�C�p�����[�^.���Z���敪 = 4:��������̏ꍇ <BR>
     * �@@�@@�@@�@@�@@- wk�R�[�h��"4"���Z�b�g���� <BR>
     * <BR>
     * �@@�@@�@@�D�p�����[�^.���Z���敪����L�ȊO�̏ꍇ�inull���܂ށj <BR>
     * �@@�@@�@@�@@�@@- �S�j�l�Z�b�g ���s�� <BR>
     * �@@�@@�@@�@@�@@�@@�@@�l�F�@@�ɂ�null��ݒ肷�� <BR>
     * <BR>
     * <BR>
     * �R�j�\�����b�Z�[�W�̎擾  <BR>
     * <BR>
     * �@@QueryProcessor.doFindAllQuery()���R�[������B  <BR>
     * <BR>
     * �@@[doFindAllQuery()�ɃZ�b�g����p�����[�^]  <BR>
     * �@@arg0�F�@@�R�[�h�|��e�[�u��(code_translation)  <BR>
     * �@@arg1�F�@@�I�v�V���i��������(*1�j  <BR>
     * �@@arg2�F�@@�I�u�W�F�N�g�z��i*2�j  <BR>
     * <BR>
     * �@@���擾�ł��Ȃ��ꍇ�Anull��ԋp����B <BR>
     * <BR>
     * �@@�i*1�j�I�v�V���i��������  <BR>
     * <BR>
     * �@@�@@�@@�ȉ��̕�������쐬����B  <BR>
     * �@@�@@�@@�@@"code_type = 'pls_terdv' "  <BR>
     * �@@�@@�@@�{" and institution_code = ? "  <BR>
     * �@@�@@�@@�{" and code = ?"  <BR>
     * <BR>
     * �@@�i*2�j�I�u�W�F�N�g�z��  <BR>
     * <BR>
     * �@@�@@�@@�ȉ��̏���ArrayList���쐬����B  <BR>
     * �@@�@@�@@�E�p�����[�^.��ЃR�[�h  <BR>
     * �@@�@@�@@�Ewk�R�[�h  <BR>
     * <BR>
     * �S�j�l�Z�b�g  <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�l�F�@@�R�j�Ŏ擾�����A�R�[�h�|��e�[�u��.�\�����b�Z�[�W<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strTermDiv - (���Z���敪)<BR>
     * ���Z���敪
     * @@param l_strInstitutionCode - (��ЃR�[�h)<BR>
     * ��ЃR�[�h
     * @@throws WEB3BaseException 
     */
    public void setTerm(
        int l_intLineNumber, String l_strTermDiv,
        String l_strInstitutionCode) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setTerm(int, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���Z���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TERM_LABEL);
        
        //�Q�j���������l�̐ݒ�  
        //[wk�ϐ���`] 
        //�Ewk�R�[�h 
        String l_strWkCode = "";
        
        //�i�P�jwk�R�[�h�ݒ� 
        //�@@�p�����[�^.���Z���敪 = 1:��ʏ��̏ꍇ 
        if (WEB3TermDivDef.NORMAL_SECTION.equals(l_strTermDiv))
        {
            //- wk�R�[�h��"1"���Z�b�g���� 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_1;
        }

        //�A�p�����[�^.���Z���敪 = 2:����M�p�̏ꍇ 
        //- wk�R�[�h��"2"���Z�b�g���� 
        else if (WEB3TermDivDef.SPECIAL_MARGIN.equals(l_strTermDiv))
        {
            //- wk�R�[�h��"2"���Z�b�g���� 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_2;
        }
        
        //�B�p�����[�^.���Z���敪 = 3:�������̏ꍇ 
        else if (WEB3TermDivDef.LONG_SECTION.equals(l_strTermDiv))
        {
            //- wk�R�[�h��"3"���Z�b�g���� 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_3;
        }
        
        //�C�p�����[�^.���Z���敪 = 4:��������̏ꍇ 
        else if (WEB3TermDivDef.LONG_SPECIAL.equals(l_strTermDiv))
        {
            //- wk�R�[�h��"4"���Z�b�g���� 
            l_strWkCode = WEB3PLSProfitLossSpecsWkCodeDef.PLS_TERDV_4;
        }
        
        //�D�p�����[�^.���Z���敪����L�ȊO�̏ꍇ�inull���܂ށj 
        //- �S�j�l�Z�b�g ���s�� 
        //�l�F�@@�ɂ�null��ݒ肷�� 
        else
        {
            l_strWkCode = null;
        }

        //�R�j�\�����b�Z�[�W�̎擾  
        //QueryProcessor.doFindAllQuery()���R�[������B  
        //[doFindAllQuery()�ɃZ�b�g����p�����[�^]  
        //arg0�F�@@�R�[�h�|��e�[�u��(code_translation)  
        //arg1�F�@@�I�v�V���i��������(*1�j  
        //arg2�F�@@�I�u�W�F�N�g�z��i*2�j  
        //���擾�ł��Ȃ��ꍇ�Anull��ԋp����B 
        //�i*1�j�I�v�V���i��������  
        //�ȉ��̕�������쐬����B  
        //"code_type = 'pls_terdv' "  
        //�{" and institution_code = ? "  
        //�{" and code = ?"  
        //�i*2�j�I�u�W�F�N�g�z��  
        //�ȉ��̏���ArrayList���쐬����B  
        //�E�p�����[�^.��ЃR�[�h  
        //�Ewk�R�[�h  
        List l_lisRecords = new ArrayList();
        StringBuffer l_sbWhere = new StringBuffer();
        l_sbWhere.append("code_type = 'pls_terdv' ");
        l_sbWhere.append("and institution_code = ? ");
        l_sbWhere.append("and code = ?");
        
        Object[] l_objWhere = 
            new Object[]{l_strInstitutionCode, l_strWkCode};
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_sbWhere.toString(),
                null,
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        String l_strDisplayMessage = "";
        if (l_lisRecords == null || l_lisRecords.isEmpty())
        {
            l_strDisplayMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strDisplayMessage = l_translationRow.getDisplayMessage();
        }
        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.���Z
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strDisplayMessage);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set����)<BR>
     * �u���ʁv���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.���ʃ��x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.���� <BR>
     * @@param l_strLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strQuantity - (����)<BR>
     * ����
     */
    public void setQuantity(String l_strLineNumber, String l_strQuantity)
    {
        final String STR_METHOD_NAME = " setQuantity(String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���ʃ��x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(QUANTITY_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.����
        this.setValue(Integer.parseInt(l_strLineNumber), l_gentradeCsvColumnModel, l_strQuantity);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���n��)<BR>
     * �u���n���v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR> 
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.���n�����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.���n�� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_datPassDate - (���n��)<BR>
     * ���n��
     */
    public void setPassDate(int l_intLineNumber, Date l_datPassDate)
    {
        final String STR_METHOD_NAME = " setPassDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���n�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PASSDATE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.���n��
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datPassDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���n���z)<BR>
     * �u���n���z�v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.���n���z���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.���n���z <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strPassAmount - (���n���z)<BR>
     * ���n���z
     */
    public void setPassAmount(int l_intLineNumber, String l_strPassAmount)
    {
        final String STR_METHOD_NAME = " setPassAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���n���z���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PASSAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.���n���z
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strPassAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�擾��)<BR>
     * �u�擾���v���Z�b�g����B  <BR>
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�擾�����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�擾��<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_datGetDate - (�擾��)<BR>
     * �擾��
     */
    public void setGetDate(int l_intLineNumber, Date l_datGetDate)
    {
        final String STR_METHOD_NAME = " setGetDate(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�擾�����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(GETDATE_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�擾��
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datGetDate);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�擾�)<BR>
     * �u�擾��v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�擾����x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�擾� <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strGetAmount - (�擾�)<BR>
     * �擾�
     */
    public void setGetAmount(int l_intLineNumber, String l_strGetAmount)
    {
        final String STR_METHOD_NAME = " setGetAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�擾����x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(GETAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�擾�
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strGetAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���v)<BR>
     * �u���v�v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.���v���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.���v <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strProlossAmount - (���v)<BR>
     * ���v
     */
    public void setProlossAmount(int l_intLineNumber, String l_strProlossAmount)
    {
        final String STR_METHOD_NAME = " setProlossAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.���v���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(PROLOSSAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.���v
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strProlossAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�݌v���v)<BR>
     * �u�݌v���v�v���Z�b�g����B<BR>  
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�݌v���v���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�݌v���v<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strTotalProlossAmount - (�݌v���v)<BR>
     * �݌v���v
     */
    public void setTotalProlossAmount(int l_intLineNumber, String l_strTotalProlossAmount)
    {
        final String STR_METHOD_NAME = " setTotalProlossAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�݌v���v���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TOTALPROLOSSAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�݌v���v
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTotalProlossAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ېőΏۊz)<BR>
     * �u�ېőΏۊz�v���Z�b�g����B <BR> 
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�ېőΏۊz���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�ېőΏۊz <BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strTaxableAmount - (�ېőΏۊz)<BR>
     * �ېőΏۊz
     */
    public void setTaxableAmount(int l_intLineNumber, String l_strTaxableAmount)
    {
        final String STR_METHOD_NAME = " setTaxableAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�ېőΏۊz���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(TAXABLEAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�ېőΏۊz
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTaxableAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����Ŋz)<BR>
     * �u�����Ŋz�v���Z�b�g����B  <BR>
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�����Ŋz���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�����Ŋz<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strCollectTaxAmount - (�����Ŋz)<BR>
     * �����Ŋz
     */
    public void setCollectTaxAmount(int l_intLineNumber, String l_strCollectTaxAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�����Ŋz���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�����Ŋz
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����Ŋz�i���Łj)<BR>
     * �u�����Ŋz�i���Łj�v���Z�b�g����B  <BR>
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�����Ŋz�i���Łj���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�����Ŋz�i���Łj<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strCollectTaxNAmount - (�����Ŋz�i���Łj)<BR>
     * �����Ŋz�i���Łj
     */
    public void setCollectTaxNAmount(int l_intLineNumber, String l_strCollectTaxNAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxNAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�����Ŋz�i���Łj���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXNAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�����Ŋz�i���Łj
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxNAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�����Ŋz�i�n���Łj)<BR>
     * �u�����Ŋz�i�n���Łj�v���Z�b�g����B<BR>  
     * <BR>
     * �P�j�J�������f���擾  <BR>
     * �@@�@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B  <BR>
     * <BR>
     * �@@�@@[get�J�������f��()�Ɏw�肷�����]  <BR>
     * �@@�@@���ڃ��x���F�@@���v����CSV.�����Ŋz�i�n���Łj���x��  <BR>
     * <BR>
     * �Q�j�l�Z�b�g  <BR>
     * �@@�@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B  <BR>
     * <BR>
     * �@@�@@[set���ڒl()�Ɏw�肷�����]  <BR>
     * �@@�@@�s�ԍ��F�@@�����̍s�ԍ�  <BR>
     * �@@�@@�J�����F�@@�P�j�Ŏ擾�����J�������f��  <BR>
     * �@@�@@�l�F�@@�p�����[�^.�����Ŋz�i�n���Łj<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�
     * @@param l_strCollectTaxLAmount - (�����Ŋz�i�n���Łj)<BR>
     * �����Ŋz�i�n���Łj
     */
    public void setCollectTaxLAmount(int l_intLineNumber, String l_strCollectTaxLAmount)
    {
        final String STR_METHOD_NAME = " setCollectTaxLAmount(int, String)";
        log.entering(STR_METHOD_NAME);
        
        
        //�P�j�J�������f���擾
        //this.get�J�������f��()�ɂāACSV�J�������f�����擾����B
        //[get�J�������f��()�Ɏw�肷�����]
        //���ڃ��x���F ���v����CSV.�����Ŋz�i�n���Łj���x��
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = 
            this.getColumnModel(COLLECTTAXLAMOUNT_LABEL);

        //�Q�j�l�Z�b�g
        //this.set���ڒl()�ɂč��ڒl���Z�b�g����B
        //[set���ڒl()�Ɏw�肷�����]  
        //�s�ԍ��F�@@�����̍s�ԍ�  
        //�J�����F�@@�P�j�Ŏ擾�����J�������f��  
        //�l�F�@@�p�����[�^.�����Ŋz�i�n���Łj
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCollectTaxLAmount);
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
