head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoInvalidOperationCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����I�y���[�V��������CSV(WEB3AdminIpoInvalidOperationCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/13 �ė� (���u) �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>038
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.104�C��
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����I�y���[�V��������CSV)<BR>
 * �u�b�N�r���f�B���O�󋵃_�E�����[�h�ō쐬����<BR>
 * �����I�y���[�V��������CSV�t�@@�C���f�[�^�N���X<BR>
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoInvalidOperationCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoInvalidOperationCsv.class);
    
    /**
     * �萔��`�v���p�e�B�@@�h���X�R�[�h�h
     */
    public static final String BRANCH_CODE_LABEL = "���X�R�[�h";
    
    /**
     * �萔��`�v���p�e�B�@@�h�ڋq�R�[�h�h
     */
    public static final String ACCOUNT_CODE_LABEL = "�ڋq�R�[�h";
    
    /**
     * �萔��`�v���p�e�B�@@�h�ڋq���h
     */
    public static final String ACCOUNT_NAME_LABEL = "�ڋq��";
    
    /**
     * �萔��`�v���p�e�B�@@�h��t�����h
     */
    public static final String RECEPTION_DATE_LABEL = "��t����";
    
    /**
     * �萔��`�v���p�e�B�@@�h�������e�h
     */
    public static final String PROCESSING_LABEL = "�������e";
    
    /**
     * �萔��`�v���p�e�B�@@�h�\�����ʁh
     */
    public static final String ORDER_QUNTITY_LABEL = "�\������";
    
    /**
     * �萔��`�v���p�e�B�@@�h�\�����i�h
     */
    public static final String ORDER_PRICE_LABEL = "�\�����i";
    
    /**
     * �萔��`�v���p�e�B�@@�h�w�l�^���s�h
     */
    public static final String LIMIT_PRICE_MARKET_ORDER_LABEL = "�w�l�^���s";
    
    /**
     * (�w�l�\��)<BR>
     * �萔��`�v���p�e�B�@@�w�l<BR>
     */
    public static final String LIMIT_PRICE_INDICATION = "�w�l";
    
    /**
     * (���s�\��)<BR>
     * �萔��`�v���p�e�B�@@���s<BR>
     */
    public static final String MARKET_ORDER_INDICATION = "���s";
    
    /**
     * �萔��`�v���p�e�B�@@�������e_�V�K�\��
     */
    public static final String PROCESSING_ORDERED_INDICATION = "�V�K";
    
    /**
     * �萔��`�v���p�e�B�@@�������e_�����\��
     */
    public static final String PROCESSING_CHANGE_INDICATION = "����";
    
    /**
     * �萔��`�v���p�e�B�@@�������e_����\��
     */
    public static final String PROCESSING_CANCEL_INDICATION = "���";

    /**
     * (���҃R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���҃R�[�h�h<BR>
     */
    public static final String TRADER_CODE_LABEL = "���҃R�[�h";
    
    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.WEB3AdminIpoInvalidOperationCsv
     * @@roseuid 40F21E4701F5
     */
    public WEB3AdminIpoInvalidOperationCsv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        super();
        createKeyHeader(l_ipoProduct);
        createColumnHeader();
     
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.���҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.��t�������x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�������e���x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�\�����ʃ��x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�\�����i���x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�w�l�^���s���x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * @@roseuid 40F21E4701F7
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[9];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] = 
            new WEB3GentradeCsvColumnModel(TRADER_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[2] = 
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[3] = 
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[4] = 
            new WEB3GentradeCsvColumnModel(
                RECEPTION_DATE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        columnHeader[5] = 
            new WEB3GentradeCsvColumnModel(PROCESSING_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[6] = 
            new WEB3GentradeCsvColumnModel(ORDER_QUNTITY_LABEL, 6, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[7] = 
            new WEB3GentradeCsvColumnModel(ORDER_PRICE_LABEL, 7, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[8] = 
            new WEB3GentradeCsvColumnModel(
                LIMIT_PRICE_MARKET_ORDER_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);
        
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����A<BR>
     * set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�L�[�w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@���ݓ�����"yyyy/MM/dd HH:mm:ss"�̌`����format����������B<BR>
     * <BR>
     * (*1) ���ݓ���<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@IPO����.�����R�[�h<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@IPO����.������<BR>
     * <BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40F21E4701F8
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createKeyHeader(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeader = new String[3];
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(l_processTime, "yyyy/MM/dd HH:mm:ss");
        l_strKeyHeader[1] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        l_strKeyHeader[2] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();
        
        setKeyHeader(l_strKeyHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.���X�R�[�h���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���X�h�c�ɊY�����镔�X�I�u�W�F�N�g.getBranchCode()<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngBranchId - ���X�h�c
     * @@roseuid 40F21E4701FA
     */
    public void setBranchCode(int l_intLineNumber, long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BRANCH_CODE_LABEL);
            
            //���X�h�c�ɊY�����镔�X�I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId); //throw NotFoundException
            String l_strCode = l_branch.getBranchCode();
            
            //�l�Z�b�g
            this.setValue(l_intLineNumber, l_csvDataModel, l_strCode);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�ڋq)<BR>
     * �ڋq�R�[�h�A�ڋq�����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾<BR>
     * �@@�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�i�ڋq�R�[�h�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�ڋq�R�[�h���x��<BR>
     * <BR>
     * �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.getAccountCode()�̍�6byte<BR>
     * <BR>
     * �S�j�@@�i�ڋq���j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�ڋq�����x��<BR>
     * <BR>
     * �T�j�@@�i�ڋq���j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�S�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.get�ڋq�\����()<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngAccountId - �����h�c
     * @@roseuid 40F21E470205
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " setAccount(int, long)";
        log.entering(STR_METHOD_NAME);
                
        try
        {
            //�ڋq�I�u�W�F�N�g�擾
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            MainAccount l_mainAccount= l_finApp.getAccountManager().getMainAccount(l_lngAccountId); //throw NotFoundException
            
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //�i�ڋq�R�[�h�j�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode = this.getColumnModel(ACCOUNT_CODE_LABEL);
            
            //�i�ڋq�R�[�h�j�l�Z�b�g
            String l_strCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0,6));
            
            //�i�ڋq���j�J�������f���擾
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);
            
            //�i�ڋq���j�l�Z�b�g
			// 2004/12/08 ��Q�Ǘ��[No.U00529 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA START
			String l_strName = l_mainAccountRow.getFamilyName();
//            String l_strName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();
			// 2004/12/08 ��Q�Ǘ��[No.U00529 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA END
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);
            
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set��t����)<BR>
     * ��t�������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.��t�������x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�쐬����<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_tsCreatedTimestamp - �쐬����
     * @@roseuid 40F21E470208
     */
    public void setReceptionDate(int l_intLineNumber, Timestamp l_tsCreatedTimestamp) 
    {
        final String STR_METHOD_NAME = " setReceptionDate(int, Timestamp)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(RECEPTION_DATE_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_tsCreatedTimestamp);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�������e)<BR>
     * �������e���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�������e���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.ORDERED�j�̏ꍇ�A<BR>�����I�y���[�V��������CSV.�\�����_�V�K�\���B<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.MODIFIED�j�̏ꍇ�A<BR>�����I�y���[�V��������CSV.�\�����_�����\���B<BR>
     * �@@�@@�i�u�b�N�r���f�B���O�\����� == 
     * OrderStatusEnum.CANCELLED�j�̏ꍇ�A<BR>�����I�y���[�V��������CSV.�\�����_����\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_orderStatus - �u�b�N�r���f�B���O�\�����
     * @@roseuid 40F21EF0031E
     */
    public void setProcessing(int l_intLineNumber, OrderStatusEnum l_orderStatus) 
    {
        final String STR_METHOD_NAME = " setProcessing(int, OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);
        
        if (l_orderStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(PROCESSING_LABEL);
        
        //�l�Z�b�g
        String l_strProcessing = null;
        
        if ((OrderStatusEnum.ORDERED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_ORDERED_INDICATION;
        }
        else if ((OrderStatusEnum.MODIFIED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CHANGE_INDICATION;
        }
        else if ((OrderStatusEnum.CANCELLED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CANCEL_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strProcessing);
        
        log.exiting(STR_METHOD_NAME);
    }
     
    
    /**
     * (set�\������)<BR>
     * �\�����ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�\�����ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�\������<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblOrderQuantity - �\������
     * @@roseuid 40F21E47020B
     */
    public void setOrderQuantity(int l_intLineNumber, double l_dblOrderQuantity) 
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_QUNTITY_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�\�����i)<BR>
     * �\�����i���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�\�����i���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�\�����i<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblOrderPrice - �\�����i
     * @@roseuid 40F21E47020E
     */
    public void setOrderPrice(int l_intLineNumber, double l_dblOrderPrice) 
    {
        final String STR_METHOD_NAME = " setOrderPrice(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_PRICE_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderPrice));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�w�l�^���s�l)<BR>
     * �w�l�^���s���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�����I�y���[�V��������CSV.�w�l�^���s���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�����̎w�l == 0�j�̏ꍇ�A�����I�y���[�V��������CSV.���s�\���B<BR>
     * �@@�@@�ȊO�A�����I�y���[�V��������CSV.�w�l�\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblLimitPrice - �w�l�B
     * @@roseuid 40F21E470211
     */
    public void setLimitPriceMarketOrder(int l_intLineNumber, double l_dblLimitPrice) 
    {
        final String STR_METHOD_NAME = " setLimitPriceMarketOrder(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LIMIT_PRICE_MARKET_ORDER_LABEL);
        
        //�l�Z�b�g
        String l_strIndication = null;
        if (l_dblLimitPrice == 0)
        {
            l_strIndication = MARKET_ORDER_INDICATION;
        }
        else
        {
            l_strIndication = LIMIT_PRICE_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strIndication);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set���҃R�[�h)<BR>
     * ���҃R�[�h���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�ڋq�I�u�W�F�N�g�擾<BR>
     * �@@�����h�c�ɊY������ڋq�I�u�W�F�N�g���擾����B<BR>
     * <BR>
     * �Q�j�@@�i���҃R�[�h�j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�@@�����I�y���[�V��������CSV.���҃R�[�h���x��<BR>
     * <BR>
     * �R�j�@@�i���҃R�[�h�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�ڋq.getSonarTraderCode()<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngAccountId - ����ID
     * @@throws WEB3BaseException
     */
    public void setTradeCode(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setTradeCode(int, long)";
        log.entering(STR_METHOD_NAME);
     
        //�P�j�@@�ڋq�I�u�W�F�N�g�擾
        MainAccount l_mainAccount = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("�e�[�u���ɊY������f�[�^������܂���");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //�Q�j�@@�i���҃R�[�h�j�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(TRADER_CODE_LABEL);
        
        //�R�j�@@�i���҃R�[�h�j�l�Z�b�g
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        this.setValue(l_intLineNumber, l_csvColumnModel, l_mainAccountRow.getSonarTraderCode());
        
        log.exiting(STR_METHOD_NAME);
    }    
}
@
