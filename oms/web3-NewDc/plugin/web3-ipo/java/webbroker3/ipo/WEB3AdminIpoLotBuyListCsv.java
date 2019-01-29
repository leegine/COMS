head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotBuyListCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʁE�w���\����CSV(WEB3AdminIpoLotBuyListCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 �ė� (���u) �V�K�쐬
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>071
Revesion History : 2005/01/21 ���(SRA) �c�Ή�>>>038
Revesion History : 2005/12/20 杊��] (���u) �d�l�ύXNo.104�C��
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (���I���ʁE�w���\����CSV)<BR>
 * ���I���ʁE�w���\���󋵃_�E�����[�h�ō쐬����CSV�t�@@�C���f�[�^�N���X<BR>
 *                                                               
 * @@author �ė�
 * @@version 1.0
 */
public class WEB3AdminIpoLotBuyListCsv extends WEB3GentradeCsvDataModel 
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotBuyListCsv.class);
    
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
     * �萔��`�v���p�e�B�@@�h���I���ʁh
     */
    public static final String LOT_RESULT_LABEL = "���I����";
    
    /**
     * �萔��`�v���p�e�B�@@�h���I���ʁh
     */
    public static final String ELECTED_QUANTITY_LABEL = "���I����";
    
    /**
     * �萔��`�v���p�e�B�@@�h�w���\�����ʁh
     */
    public static final String APPLICATION_QUANTITY_LABEL = "�w���\������";
    
    /**
     * �萔��`�v���p�e�B�@@�h�w���\���^���ޓ����h
     */
    public static final String OFFERING_TIMESTAMP_LABEL = "�w���\���^���ޓ���";
    
    /**
     * �萔��`�v���p�e�B�@@�h�w���\����ԁh
     */
    public static final String OFFER_STATE_LABEL = "�w���\�����";
    
    /**
     * �萔��`�v���p�e�B�@@�h��t��ԁh
     */
    public static final String ACCEPT_STATUS_LABEL = "��t���";
    
    /**
     * �萔��`�v���p�e�B�@@�h�D�揇�ʁh
     */
    public static final String SUBSTITUTE_PRIORITY = "�D�揇��";
    
    /**
     * �萔��`�v���p�e�B�@@���I����_���I�\��
     */
    public static final String LOT_RESULT_PRIZE_INDICATION = "���I";
    
    /**
     * �萔��`�v���p�e�B�@@���I����_�⌇�\��
     */
    public static final String LOT_RESULT_WAITING_INDICATION = "�⌇";
    
    /**
     * �萔��`�v���p�e�B�@@���I����_�⌇���I�\��
     */
    public static final String LOT_RESULT_WAITING_PRIZE_INDICATION = "�⌇���I";
    
    /**
     * �萔��`�v���p�e�B�@@���I����_�⌇���I�\��
     */
    public static final String LOT_RESULT_WAITING_REJECTED_INDICATION = "�⌇���I";
    
    /**
     * �萔��`�v���p�e�B�@@���I����_���I�\��
     */
    public static final String LOT_RESULT_REJECTED_INDICATION = "���I";
    
    /**
     * �萔��`�v���p�e�B�@@�w���\�����_�\���\��
     */
    public static final String OFFER_STATE_APPLICATION_INDICATION = "�\��";
    
    /**
     * �萔��`�v���p�e�B�@@�w���\�����_���ޕ\��
     */
    public static final String OFFER_STATE_CANCEL_INDICATION = "����";
    
    /**
     * �萔��`�v���p�e�B�@@�w���\�����_�Ȃ��\��
     */
    public static final String OFFER_STATE_NO_INDICATION = "----";
    
    /**
     * �萔��`�v���p�e�B�@@��t���_��t���\��
     */
    public static final String RECEIVE_STATE_RECEIVE_INDICATION = "��t��";
    
    /**
     * �萔��`�v���p�e�B�@@��t���_��t�ϕ\��
     */
    public static final String RECEIVE_STATE_RECEIVE_END_INDICATION = "��t��";
    
    /**
     * �萔��`�v���p�e�B�@@��t���_�Ȃ��\��
     */
    public static final String RECEIVE_STATE_NO_INDICATION = "----";
    
    /**
     * (���҃R�[�h���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���҃R�[�h�h<BR>
     */
    public static final String TRADER_CODE_LABEL = "���҃R�[�h";
    
    /**
     * (���J���i���x��)<BR>
     * �萔��`�v���p�e�B�@@�h���J���i�h<BR>
     */
    public static final String PUBLIC_OFFERING_PRICE_LABEL = "���J���i";
    
    /**
     * (�M�p�敪���x��)<BR>
     * �萔��`�v���p�e�B�@@�h�M�p�敪�h<BR>
     */
    public static final String MARGIN_DIV_LABEL = "�M�p�敪";
    
    /**
     * �萔��`�v���p�e�B�@@�M�p�����J�ݕ\��
     */
    public static final String MARGIN_ACC_OPEN = "�J��";
    
    /**
     * �萔��`�v���p�e�B�@@�M�p�������J�ݕ\��
     */
    public static final String MARGIN_ACC_CLOSE = "----";
    
    /**
     * (���I�ԍ����x��)<BR>
     * �萔��`�v���p�e�B�@@�h���I�ԍ��h<BR>
     */
    public static final String LOT_NUMBER_LABEL = "���I�ԍ�";

    /**
     * �R���X�g���N�^�B<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * �@@�|super()�ɂăC���X�^���X�𐶐�����B<BR>
     * �@@�|create�L�[�w�b�_(IPO����)���R�[�����A�L�[�w�b�_�����쐬����B<BR>
     * �@@�|create�J�����w�b�_()���R�[�����A�J�����w�b�_�����쐬����B<BR>
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.WEB3AdminIpoLotBuyListCsv
     * @@roseuid 40ECA86302E8
     */
    public WEB3AdminIpoLotBuyListCsv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        super();
        createKeyHeader(l_ipoProduct);
        createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����A<BR>set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���҃R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���J���i���x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 6<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 6<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 7<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\�����ʃ��x��<BR>
     * �@@�J�����ԍ��F 7<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 8<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\���^���ޓ������x��<BR>
     * �@@�J�����ԍ��F 8<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���t����<BR>
     * �@@���t�t�H�[�}�b�g�F�@@<BR>
     * �@@�@@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * �|�@@index = 9<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\����ԃ��x��<BR>
     * �@@�J�����ԍ��F 9<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 10<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.��t��ԃ��x��<BR>
     * �@@�J�����ԍ��F 10<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 11<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�D�揇�ʃ��x��<BR>
     * �@@�J�����ԍ��F 11<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�ilong�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 40ECA8630307
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[14];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(TRADER_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            
        /**
         * �M�p�敪�ǉ� 2006.04.27
         * SCS����
         */
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(
                MARGIN_DIV_LABEL, 
                4, 
                WEB3GentradeCsvColumnModel.STRINGTYPE, 
                null);            
   
        columnHeader[5] =
            new WEB3GentradeCsvColumnModel(
                PUBLIC_OFFERING_PRICE_LABEL, 
                5, 
                WEB3GentradeCsvColumnModel.DOUBLETYPE, 
                null);
        columnHeader[6] =
            new WEB3GentradeCsvColumnModel(LOT_RESULT_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[7] =
            new WEB3GentradeCsvColumnModel(ELECTED_QUANTITY_LABEL, 7, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[8] =
            new WEB3GentradeCsvColumnModel(APPLICATION_QUANTITY_LABEL, 8, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[9] =
            new WEB3GentradeCsvColumnModel(
                OFFERING_TIMESTAMP_LABEL, 
                9, 
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, 
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        columnHeader[10] =
            new WEB3GentradeCsvColumnModel(OFFER_STATE_LABEL, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[11] =
            new WEB3GentradeCsvColumnModel(ACCEPT_STATUS_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[12] =
            new WEB3GentradeCsvColumnModel(SUBSTITUTE_PRIORITY, 12, WEB3GentradeCsvColumnModel.LONGTYPE, null);
        
        /**
         * ���I�ԍ��ǉ� 2006.04.27
         * SCS����
         */
        columnHeader[13] =
            new WEB3GentradeCsvColumnModel(LOT_NUMBER_LABEL, 13, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (create�L�[�w�b�_)<BR>
     * �L�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ蕶����̔z��𐶐����A<BR>set�L�[�w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
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
     * @@roseuid 40ECA8630326
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
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���X�R�[�h���x��<BR>
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
     * @@roseuid 40ECA8630346
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
            String l_strValue = l_branch.getBranchCode();
            
            //�l�Z�b�g
            this.setValue(l_intLineNumber, l_csvDataModel, l_strValue);
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
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�ڋq�R�[�h���x��<BR>
     * <BR>
     * �R�j�@@�i�ڋq�R�[�h�j�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�Q�j�Ŏ擾�����J�������f��<BR>
     *   �l�F�@@�ڋq.getAccountCode()�̍�6byte<BR>
     * <BR>
     * �S�j�@@�i�ڋq���j�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�ڋq�����x��<BR>
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
     * @@roseuid 40ECA8630355
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
			// 2004/12/10 ��Q�Ǘ��[No.U00545 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA START
			String l_strName = l_mainAccountRow.getFamilyName();
//            String l_strName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();
			// 2004/12/10 ��Q�Ǘ��[No.U00545 �ڋq���̒l���u�ڋq�̖��O(�c��)�{ "�@@" �{���O(���O)�v�ɂȂ�悤�ɏC���B���@@SRA END
			
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
     * (set���I����)<BR>
     * ���I���ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.��t���ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i���I���� == �h���I�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.���I����_���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.���I����_�⌇���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �h���I�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.���I����_�⌇���I�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h�⌇�h && ���I���ʁi�J��j == �hDEFAULT�i�����I�j�h�j �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.���I����_�⌇�\���B<BR>
     * <BR>
     * �@@�@@�i���I���� == �h���I�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.���I����_���I�\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_strLotResult - ���I����<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����I�j<BR>
     * 1�F�@@���I<BR>
     * 2�F�@@�⌇<BR>
     * 3�F�@@���I<BR>
     * 
     * @@param l_strLotResultRetry - ���I���ʁi�J��j<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����I�j<BR>
     * 1�F�@@���I<BR>
     * 3�F�@@���I<BR>
     * @@roseuid 40ECAD250077
     */
    public void setLotResult(int l_intLineNumber, String l_strLotResult, String l_strLotResultRetry) 
    {
        final String STR_METHOD_NAME = " setLotResult(int, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_RESULT_LABEL);
        
        //�l�Z�b�g
        String l_strResult = null;
        if (WEB3LotResultDef.ELECTION.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_PRIZE_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_PRIZE_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_REJECTED_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_INDICATION;
        }
        else if (WEB3LotResultDef.DEFEAT.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_REJECTED_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strResult);
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set���I����)<BR>
     * ���I���ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���I���ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���I����<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblElectedQuantity - ���I����
     * @@roseuid 40ECA8630394
     */
    public void setElectedQuantity(int l_intLineNumber, double l_dblElectedQuantity) 
    {
        final String STR_METHOD_NAME = " setElectedQuantity(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ELECTED_QUANTITY_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblElectedQuantity));
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set�w���\������)<BR>
     * �w���\�����ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\�����ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�w���\������<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblApplicationQuantity - �w���\������
     * @@roseuid 40ECA86303B3
     */
    public void setApplicationQuantity(int l_intLineNumber, double l_dblApplicationQuantity) 
    {
        final String STR_METHOD_NAME = " setApplicationQuantity(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(APPLICATION_QUANTITY_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblApplicationQuantity));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�w���\���^���ޓ���)<BR>
     * �w���\���^���ޓ������Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\���^���ޓ������x��
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�w���\���^���ޓ���<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_datOfferingTimestamp - �w���\���^���ޓ���
     * @@roseuid 40ECB2E800E4
     */
    public void setOfferingTimestamp(int l_intLineNumber, Date l_datOfferingTimestamp) 
    {
        final String STR_METHOD_NAME = " setOfferingTimestamp(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(OFFERING_TIMESTAMP_LABEL);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_datOfferingTimestamp);
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set�w���\�����)<BR>
     * �w���\����Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�w���\����ԃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i�w���\���敪 == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.�w���\�����_�Ȃ��\���B<BR>
     * <BR>
     * �@@�@@�i�w���\���敪 == �h�w���\���h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.�w���\�����_�\���\���B<BR>
     * <BR>
     * �@@�@@�i�w���\���敪 == �h���ށh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.�w���\�����_���ޕ\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_strOfferDiv - �w���\���敪<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����l�j<BR>
     * 1�F�@@�w���\��<BR>
     * 2�F�@@����<BR>
     * @@roseuid 40ECB3130190
     */
    public void setOfferStatus(int l_intLineNumber, String l_strOfferDiv) 
    {
        final String STR_METHOD_NAME = " setOfferStatus(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(OFFER_STATE_LABEL);
        
        //�l�Z�b�g
        String l_strOfferState = null;
        if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_NO_INDICATION;
        }
        else if (WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_APPLICATION_INDICATION;
        }
        else if (WEB3OfferingDivDef.REFUSAL.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_CANCEL_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strOfferState);
        
        log.exiting(STR_METHOD_NAME);        
    }
    
    /**
     * (set��t���)<BR>
     * ��t��Ԃ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.��t��ԃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�i��t��� == �hDEFAULT�i�����l�j�h�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.��t���_�Ȃ��\���B<BR>
     * <BR>
     * �@@�@@�i��t��� == �hSONAR���M�ρh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.��t���_��t���\���B<BR>
     * <BR>
     * �@@�@@�i��t��� == �hSONAR���f�ρh�j�̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.��t���_��t�ϕ\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_strAcceptStatus - ��t���<BR>
     * <BR>
     * 0�F�@@DEFAULT�i�����l�j<BR>
     * 1�F�@@SONAR���M��<BR>
     * 2�F�@@SONAR���f��<BR>
     * @@roseuid 40ECB3D2027B
     */
    public void setAcceptStatus(int l_intLineNumber, String l_strAcceptStatus) 
    {
        final String STR_METHOD_NAME = " setAcceptStatus(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ACCEPT_STATUS_LABEL);
        
        //�l�Z�b�g
        String l_strState = null;
        if (WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_NO_INDICATION;
        }
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_MAIL_SENDED.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_RECEIVE_INDICATION;
        }
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_REFLECTED.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_RECEIVE_END_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strState);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�D�揇��)<BR>
     * �D�揇�ʂ��Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�D�揇�ʃ��x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�D�揇��<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40ECB47B0077
     */
    public void setSubstitutePriority(int l_intLineNumber, Long l_lngSubstitutePriority) 
    {
        final String STR_METHOD_NAME = " setSubstitutePriority(int, Long)";
        log.entering(STR_METHOD_NAME);
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(SUBSTITUTE_PRIORITY);
        
        //�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_lngSubstitutePriority);
        
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
     * �@@���ڃ��x���F�@@�@@���I���ʁE�w���\����CSV.���҃R�[�h���x��<BR>
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
    
    /**
     * (set���J���i)<BR>
     * ���J���i���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@�@@���I���ʁE�w���\����CSV.���J���i���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@�����̌��J���i<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_dblPublicOfferingPrice - ���J���i
     */
    public void setPublicOfferingPrice(int l_intLineNumber, Double l_dblPublicOfferingPrice)
    {
        final String STR_METHOD_NAME = " setPublicPrice(int, Double)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�J�������f���擾
        WEB3GentradeCsvColumnModel l_cvsColumnModel = this.getColumnModel(PUBLIC_OFFERING_PRICE_LABEL);
        
        //�Q�j�@@�l�Z�b�g
        this.setValue(l_intLineNumber, l_cvsColumnModel, l_dblPublicOfferingPrice);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set�M�p�敪)<BR>
     * �M�p�敪���Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.�M�p�敪���x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@<BR>
     * �@@�@@�����F�M�p�����J�ݏ� = true �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.�M�p�����J�ݕ\���B<BR>
     * <BR>
     * �@@�@@�����F�M�p�����J�ݏ� = false �̏ꍇ�A<BR>
     * �@@�@@�@@�|���I���ʁE�w���\����CSV.�M�p�������J�ݕ\���B<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngAccountId - ����ID
     * @@author SCS���� 2006.04.27
     * <BR>
     */
    public void setMarginDiv(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " setMarginDiv(int, long)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@�ڋq�I�u�W�F�N�g�擾
        WEB3GentradeMainAccount l_genMainAccount = null;
//        MainAccount l_mainAccount = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_genMainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
//            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
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
        
        //�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(MARGIN_DIV_LABEL);
        
        //�l�Z�b�g
        String l_strState = null;
        if (l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_strState = MARGIN_ACC_OPEN;
        }
        else
        {
            l_strState = MARGIN_ACC_CLOSE;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strState);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set���I�ԍ�)<BR>
     * ���I�ԍ����Z�b�g����B<BR>
     * <BR>
     * �P�j�@@�J�������f���擾<BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     * �@@[get�J�������f��()�Ɏw�肷�����]<BR>
     * �@@���ڃ��x���F�@@���I���ʁE�w���\����CSV.���I�ԍ����x��<BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g<BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     * �@@[set���ڒl()�Ɏw�肷�����]<BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ�<BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR>
     * �@@�l�F�@@���I�ԍ�<BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_strLotNumber - ���I�ԍ�
     * @@author SCS�����@@2006.04.27
     */
    public void setLotNumber(int l_intLineNumber, String l_strLotNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setLotNumber(int, String)";
        log.entering(STR_METHOD_NAME);
                
        //�P�j�J�������f���擾
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_NUMBER_LABEL);
        
        //�Q�j�i���I�ԍ��j�l�Z�b�g
        this.setValue(l_intLineNumber, l_csvDataModel, l_strLotNumber);
        
        log.exiting(STR_METHOD_NAME);        
    }    
}
@
