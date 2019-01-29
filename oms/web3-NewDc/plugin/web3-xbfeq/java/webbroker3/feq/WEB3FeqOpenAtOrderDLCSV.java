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
filename	WEB3FeqOpenAtOrderDLCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ��t�����_�E�����[�hCSV(WEB3FeqOpenAtOrderDLCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                   2005/08/03 ����(���u) ���r���[     
*/

package webbroker3.feq;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.define.WEB3FeqOpenAtOrderDLDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (��t�����_�E�����[�hCSV)<BR>
 * ��t�����_�E�����[�hCSV�N���X<BR>
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3FeqOpenAtOrderDLCSV extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOpenAtOrderDLCSV.class);
    
    /**
     * (�^�p�R�[�h���x��)<BR>
     * �^�p�R�[�h���x��<BR>
     */
    public String ORDER_EMP_CODE_LABEL = "�^�p�R�[�h";
    
    /**
     * (���n�����R�[�h���x��)<BR>
     * ���n�����R�[�h���x��<BR>
     */
    public String OFFSHORE_PRODUCT_CODE_LABEL = "���n�����R�[�h";
    
    /**
     * (���������x��)<BR>
     * ���������x��<BR>
     */
    public String PRODUCT_NAME_LABEL = "������";
    
    /**
     * (�����敪���x��)<BR>
     * �����敪���x��<BR>
     * <BR>
     * ���ݒ�l�̐�����<BR>
     * S�F���t<BR>
     * B�F���t<BR>
     */
    public String TRADE_DIV_LABEL = "�����敪";
    
    /**
     * (�����������x��)<BR>
     * �����������x��<BR>
     */
    public String ORDER_QUANTITY_LABEL = "��������";
    
    /**
     * (�����@@���x��)<BR>
     * �����@@���x��<BR>
     * <BR>
     * ���ݒ�l�́A"L"�Œ�<BR>
     */
    public String EXEC_METHOD_LABEL = "�����@@";
    
    /**
     * (�����P�����x��)<BR>
     * �����P�����x��<BR>
     */
    public String ORDER_PRICE_LABEL = "�����P��";
    
    /**
     * (�L���������x��)<BR>
     * �L���������x��<BR>
     * <BR>
     * ���ݒ�l�́Anull�Œ�<BR>
     */
    public String EXPIRATION_DATE_LABEL = "�L������";
    
    /**
     * (��t�����_�E�����[�hCSV)<BR>
     * �R���X�g���N�^<BR>
     * <BR>
     * �C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B<BR>
     * <BR>
     * �P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B<BR>
     * <BR>
     * �Q�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����B<BR>
     * @@roseuid 42AFFE710176
     */
    public WEB3FeqOpenAtOrderDLCSV() 
    {
        //�C���X�^���X�𐶐����A�w�b�_�����Z�b�g����B 
        //�P�jsuper()���R�[�����A�C���X�^���X�𐶐�����B
        super();
        
        //�Q�jcreate�J�����w�b�_()���R�[�����A�J�����w�b�_���𐶐�����B
        createColumnHeader();
    }
    
    /**
     * (create�J�����w�b�_)<BR>
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����A<BR>
     * set�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�^�p�R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�^�p�R�[�h���x��<BR>
     *    �J�����ԍ��F 0<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �Q�j���������R�[�h<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.���������R�[�h���x��<BR>
     *    �J�����ԍ��F 1<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �R�j������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.���������x��<BR>
     *    �J�����ԍ��F 2<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �S�j�����敪<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����敪���x��<BR>
     *    �J�����ԍ��F 3<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �T�j��������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����������x��<BR>
     *    �J�����ԍ��F 4<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�iint�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �U�j�����@@<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����@@���x��<BR>
     *    �J�����ԍ��F 5<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �V�j�����P��<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����P�����x��<BR>
     *    �J�����ԍ��F 6<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * <BR>
     * �W�j�L������<BR>
     * <BR>
     *    [CSV�J�������f���̃R���X�g���N�^�̈���]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�L���������x��<BR>
     *    �J�����ԍ��F 7<BR>
     *    ���ڌ^�F CSV�J�������f��.���ڌ^_������<BR>
     *    ���t�t�H�[�}�b�g�F null<BR>
     * @@roseuid 42AFFE710186
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        //�ȉ��̂Ƃ����CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X�ɃZ�b�g����B 
        //�P�j�^�p�R�[�h 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�^�p�R�[�h���x�� 
        //   �J�����ԍ��F 0 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.ORDER_EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�Q�j���������R�[�h 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.���������R�[�h���x�� 
        //   �J�����ԍ��F 1 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.OFFSHORE_PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�R�j������ 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.���������x�� 
        //   �J�����ԍ��F 2 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.PRODUCT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�S�j�����敪 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�����敪���x�� 
        //   �J�����ԍ��F 3 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.TRADE_DIV_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�T�j�������� 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�����������x�� 
        //   �J�����ԍ��F 4 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_���l�iint�j 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.ORDER_QUANTITY_LABEL,
            4,
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null);
            
        //�U�j�����@@ 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�����@@���x�� 
        //   �J�����ԍ��F 5 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.EXEC_METHOD_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //�V�j�����P�� 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�����P�����x�� 
        //   �J�����ԍ��F 6 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_���l�idouble�j 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.ORDER_PRICE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
            
        //�W�j�L������ 
        //
        //   [CSV�J�������f���̃R���X�g���N�^�̈���] 
        //   ���ڃ��x���F ��t�����_�E�����[�hCSV.�L���������x�� 
        //   �J�����ԍ��F 7 
        //   ���ڌ^�F CSV�J�������f��.���ڌ^_������ 
        //   ���t�t�H�[�}�b�g�F null 
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.EXPIRATION_DATE_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        setColumnHeader(l_models);    
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set�^�p�R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�^�p�R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�^�p�R�[�h<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_strOrderEmpCode - (�^�p�R�[�h)<BR>
     * �^�p�R�[�h<BR>
     * @@roseuid 42AFF2CA005D
     */
    public void setOrderEmpCode(int l_intLineNumber, String l_strOrderEmpCode) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�^�p�R�[�h���x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F ����.�^�p�R�[�h      
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_EMP_CODE_LABEL),
            l_strOrderEmpCode);
    }
    
    /**
     * (set���n�����R�[�h)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.���n�����R�[�h���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.����ID�ɊY������O����������.get���n�����R�[�h()�̖߂�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngProductId - (����ID)<BR>
     * ����ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF2D40260
     */
    public void setOffshoreProductCode(int l_intLineNumber, long l_lngProductId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setOffshoreProductCode()";
        log.entering(STR_METHOD_NAME);

        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.���n�����R�[�h���x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F ����.����ID�ɊY������O����������.get���n�����R�[�h()�̖߂�l
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
                
        WEB3FeqProduct l_feqProduct = null;

        try 
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getProduct(l_lngProductId);
        } 
        catch (NotFoundException l_ex) 
        {
            String l_strMessage = "���X�C���X�^���X���擾���Ȃ�";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
            
        }

        this.setValue(
            l_intLineNumber,
            getColumnModel(this.OFFSHORE_PRODUCT_CODE_LABEL),
            l_feqProduct.getOffshoreProductCode());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 42AFF2DA035A
     */
    public void setProductName(int l_intLineNumber) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.���������x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F null
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.PRODUCT_NAME_LABEL),
            null);
    }
    
    /**
     * (set�����敪)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����敪���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.is���t == true �̏ꍇ�A"B"<BR>
     *          ����.is���t == false �̏ꍇ�A"S"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_blnIsBuy - (is���t)<BR>
     * ���t�������ǂ����̃t���O<BR>
     * @@roseuid 42AFF2E002BE
     */
    public void setOrderType(int l_intLineNumber, boolean l_blnIsBuy) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����敪���x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F ����.is���t == true �̏ꍇ�A"B" 
        //          ����.is���t == false �̏ꍇ�A"S" 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.TRADE_DIV_LABEL),
            l_blnIsBuy ? WEB3FeqOpenAtOrderDLDef.BUY : WEB3FeqOpenAtOrderDLDef.SELL);
    }
    
    /**
     * (set��������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.��������<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngOrderQuantity - (��������)<BR>
     * ��������<BR>
     * @@roseuid 42AFF2FA01F3
     */
    public void setOrderQuantity(int l_intLineNumber, long l_lngOrderQuantity) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����������x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F ����.�������� 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_QUANTITY_LABEL),
            new Long(l_lngOrderQuantity));
    }
    
    /**
     * (set�����@@)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����@@���x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F "L"<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 42AFF30E033B
     */
    public void setExecMethod(int l_intLineNumber) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����@@���x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F "L" 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.EXEC_METHOD_LABEL),
            WEB3FeqOpenAtOrderDLDef.EXECUTE_METHOD);
    }
    
    /**
     * (set�����P��)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����P�����x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F ����.�w�l<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * 
     * @@param l_lngLimitPrice - (�w�l)<BR>
     * �w�l<BR>
     * @@roseuid 42AFF301028F
     */
    public void setOrderPrice(int l_intLineNumber, double l_lngLimitPrice) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�����P�����x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F ����.�w�l 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_PRICE_LABEL),
            new Double(l_lngLimitPrice));
    }
    
    /**
     * (set�L������)<BR>
     * �P�j�J�������f���擾<BR>
     * <BR>
     *    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR>
     * <BR>
     *    [����]<BR>
     *    ���ڃ��x���F ��t�����_�E�����[�hCSV.�L���������x��<BR>
     * <BR>
     * �Q�j�l�Z�b�g<BR>
     * <BR>
     *    this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * <BR>
     *    [����]<BR>
     *    �s�ԍ��F �����̍s�ԍ�<BR>
     *    �J�����F �P�j�Ŏ擾�����J�������f��<BR>
     *    �l�F null<BR>
     * @@param l_intLineNumber - (�s�ԍ�)<BR>
     * �s�ԍ�<BR>
     * @@roseuid 42AFF3340118
     */
    public void setExpirationDate(int l_intLineNumber) 
    {
        //    �P�j�J�������f���擾 
        //    this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B 
        //    [����] 
        //    ���ڃ��x���F ��t�����_�E�����[�hCSV.�L���������x�� 
        // �Q�j�l�Z�b�g 
        //    this.set���ڒl()�ɂč��ڒl���Z�b�g����B 
        //    [����] 
        //    �s�ԍ��F �����̍s�ԍ� 
        //    �J�����F �P�j�Ŏ擾�����J�������f�� 
        //    �l�F null 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.EXPIRATION_DATE_LABEL),
            null);
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
     * @@roseuid 42AFF37103E7
     */
    public String[] getCsvFileLines() 
    {
        final String STR_METHOD_NAME = "getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //CSV�t�@@�C���ɏo�͂���f�[�^���A�s���̔z��ɂĕԋp����B 
        //�P�j�X�[�p�[�N���X��getCSV�t�@@�C���s()���\�b�h���R�[������B 
        String[] l_csvFileLines = super.getCsvFileLines();
        
        //�Q�j�擾�����z�񂩂��s�A�u�����N�݂̂̍s�����O����B
        List l_result = new ArrayList();
        
        for (int i = 0; i < l_csvFileLines.length; i++) 
        {
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_csvFileLines[i])) 
            {
                l_result.add(l_csvFileLines[i]);
            }
        }
        //�R�j�Q�j�̌��ʂ�ԋp����B 
        String[] l_strResult = new String[l_result.size()];
        l_result.toArray(l_strResult);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strResult;
    }
    
    /**
     * (is�J�����w�b�_�s�o��)<BR>
     * CSV�t�@@�C���ɃJ�����w�b�_�s�̏o�͗v�ۂ𔻒肷��B<BR>
     * �i�I�[�o�[���C�h���\�b�h�j<BR>
     * <BR>
     * false��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 42CBA573008A
     */
    public boolean isColumnHeadOutput() 
    {
        return false;
    }
}
@
