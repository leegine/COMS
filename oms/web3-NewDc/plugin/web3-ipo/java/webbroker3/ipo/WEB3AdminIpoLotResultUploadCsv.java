head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotResultUploadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���I���ʃA�b�v���[�hCSV�N���X(WEB3AdminIpoLotResultUploadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/26 ���C�g (���u) �V�K�쐬
Revesion History : 2004/12/27 ���(SRA) �c�Ή�>>>038
Revesion History : 2005/01/05 ���(SRA) �c�Ή�>>>051
Revesion History : 2005/01/07 ���(SRA) �c�Ή�>>>071,066
Revesion History : 2007/04/19 �đo�g(���u) ���f��No.171
Revision History : 2007/07/20 ��іQ(���u) �����̖��015
*/

package webbroker3.ipo;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.gentrade.data.AdministratorUploadRow;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;


/**
 * (���I���ʃA�b�v���[�hCSV)<BR>
 * �R���X�g���N�^�B<BR>
 * <BR>
 * �C���X�^���X�𐶐����AIPO�������v���p�e�B�ɃZ�b�g����B
 * 
 * @@author ���C�g
 * @@version 1.0
 */
public class WEB3AdminIpoLotResultUploadCsv extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotResultUploadCsv.class);
    
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
     * �萔��`�v���p�e�B�@@�h�D�揇�ʁh
     */
    public static final String SUBSTITUTE_PRIORITY_LABEL = "�D�揇��";
    
    /**
     * �萔��`�v���p�e�B�@@�V�K�^�J�㒊�I���_�V�K���I
     */
    public static final String NEW_ADVANCE_LOT_TYPE_NEW_LOT = "1";
    
    /**
     * �萔��`�v���p�e�B�@@�V�K�^�J�㒊�I���_�J�㒊�I
     */
    public static final String NEW_ADVANCE_LOT_TYPE_ADVANCE_LOT = "2";
    
    /**
     * �萔��`�v���p�e�B�@@���I���ʓ��I�\��
     */
    public static final String LOT_RESULT_PRIZED = "1";
    
    /**
     * �萔��`�v���p�e�B�@@���I���ʕ⌇�\��
     */
    public static final String LOT_RESULT_WAITING = "2";
    
    /**
     * �萔��`�v���p�e�B�@@���I���ʗ��I�\��
     */
    public static final String LOT_RESULT_REJECTED = "3";
    
    /**
     * �A�b�v���[�h�t�@@�C���h�c_���I���ʃA�b�v���[�h<BR>
     * <BR>
     * ���i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u��.�A�b�v���[�h�t�@@�C���h�c�Ɋi�[���镶����
     */
    public String uploadFileId = "IPO���I���ʃA�b�v���[�h";
    
    /**
     * IPO����
     */
    private WEB3IpoProductImpl ipoProduct;
    
    /**
     * �V�K�^�J�㒊�I���<BR>
     * <BR>
     * 1�F�@@�V�K���I<BR>
     * 2�F�@@�J�㒊�I
     */
    private String newAdvanceLotType;
    
    /**
     * ���I���ʍ��v�l
     */
    private double electedQuantityTotal = 0;
    
    /**
     * �����m�萔�ʍ��v�l<BR>
     * <BR>
     * ���J�㒊�I�̏ꍇ�Ɏg�p
     */
    private double allotQuantityTotal = 0;
    
    /**
     * �R���X�g���N�^<BR>
     * ���@@�A�b�v���[�h���~�̏ꍇ�Ɏg�p����B<BR>
     * <BR>
     * �|�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����B
     * @@param l_lngUploadId - �A�b�v���[�hID<BR>
     * <BR>
     * ���@@�i�Ǘ��ҋ��ʁj�A�b�v���[�h�e�[�u����PK
     * @@return webbroker3.ipo.WEB3AdminIpoLotResultUploadCsv
     * @@roseuid 40F77E150316
     */
    public WEB3AdminIpoLotResultUploadCsv(long l_lngUploadId) 
    {
        
        final String STR_METHOD_NAME = " WEB3AdminIpoLotResultUploadCsv(long)";
        log.entering(STR_METHOD_NAME);
        
        //�����̃A�b�v���[�hID���v���p�e�B�ɃZ�b�g����
        this.administratorUploadId = l_lngUploadId;
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * �R���X�g���N�^<BR>
     * <BR>
     * �|������IPO�������v���p�e�B�ɃZ�b�g����B<BR>
     * �|this.create�J�����w�b�_()���R�[������B
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * 
     * @@return webbroker3.ipo.WEB3AdminIPOLotResultUploadCSV
     * @@roseuid 40F228DE0272
     */
    public WEB3AdminIpoLotResultUploadCsv(WEB3IpoProductImpl l_ipoProduct) 
    {
        
        final String STR_METHOD_NAME = " WEB3AdminIpoLotResultUploadCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        //������IPO�������v���p�e�B�ɃZ�b�g����
        this.ipoProduct = l_ipoProduct;
        //this.create�J�����w�b�_()���R�[������
        this.createColumnHeader();
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (get�A�b�v���[�h�t�@@�C���h�c)<BR>
     * ���I���ʃA�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����B
     * @@return String
     * @@roseuid 40F228FA031E
     */
    public String getUploadFileId() 
    {

        //���I���ʃA�b�v���[�hCSV.�A�b�v���[�h�t�@@�C���h�c��ԋp����
        return this.uploadFileId;
        
    }
    
    /**
     * (get�����^�C�v)<BR>
     * �����^�C�v��ԋp����B<BR>
     * <BR>
     * ProductTypeEnum.IPO��ԋp����B
     * @@return ProductTypeEnum
     * @@roseuid 40F38E52003D
     */
    public ProductTypeEnum getProductType() 
    {
        
        //ProductTypeEnum.IPO��ԋp����
        return ProductTypeEnum.IPO;
        
    }
    
    /**
     * (get�A�b�v���[�h����)<BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A����A�b�v���[�h���������ׂĎ擾����B<BR>
     * <BR>
     * this.get�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * [get�A�b�v���[�h����()�Ɏw�肷�����]<BR>
     * �f�[�^�L�[�F�@@this.IPO����().getIPO�����h�c()<BR>
     * @@return AdministratorUploadRow
     * @@roseuid 40F38FA50147
     */
    public AdministratorUploadRow[] getUploadActions() throws WEB3BaseException
    {
        
        //this.get�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����
        long l_lngProductId = this.getIpoProduct().getProductId();
        return this.getUploadAction(l_lngProductId);
        
    }
    
    /**
     * (get�ŐV�A�b�v���[�h����)<BR>
     * ���Y�A�b�v���[�h�t�@@�C���Ɋ֘A���钼�߂̃A�b�v���[�h�������擾����B<BR>
     * <BR>
     * this.get�ŐV�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����B<BR>
     * <BR>
     * [get�A�b�v���[�h����()�Ɏw�肷�����]<BR>
     * �f�[�^�L�[�F�@@this.IPO����().getIPO�����h�c()
     * @@return AdministratorUploadRow
     * @@roseuid 40F38FAB0137
     */
    public AdministratorUploadRow getLatestUploadAction() throws WEB3BaseException
    {
        
        long l_lngProductId = this.getIpoProduct().getProductId();
        //this.get�ŐV�A�b�v���[�h����()�ioverload���\�b�h�j���R�[�����A�߂�l��ԋp����
        return this.getLatestUploadAction(l_lngProductId);

    }
    
    /**
     * �J�����w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@�ȉ��̒ʂ�CSV�J�������f���̔z��𐶐����Aset�J�����w�b�_()�ɂăC���X�^���X��<BR>�Z�b�g����B<BR>
     * <BR>
     * [�J�����w�b�_�z��]<BR>
     * <BR>
     * �|�@@index = 0�@@�����X�R�[�h<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.���X�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 0<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 1�@@���ڋq�R�[�h<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.�ڋq�R�[�h���x��<BR>
     * �@@�J�����ԍ��F 1<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 2�@@���ڋq��<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.�ڋq�����x��<BR>
     * �@@�J�����ԍ��F 2<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 3�@@�����I����<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 3<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_������<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 4�@@�����I����<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.���I���ʃ��x��<BR>
     * �@@�J�����ԍ��F 4<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�idouble�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null<BR>
     * <BR>
     * �|�@@index = 5�@@���D�揇��<BR>
     * �@@[CSV�J�������f�� �R���X�g���N�^�̈���]<BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.�D�揇�ʃ��x��<BR>
     * �@@�J�����ԍ��F 5<BR>
     * �@@���ڌ^�F�@@CSV�J�������f��.���ڌ^_���l�ilong�j<BR>
     * �@@���t�t�H�[�}�b�g�F�@@null
     * @@roseuid 40F3D51D03A3
     */
    private void createColumnHeader() 
    {
        
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[6];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[2] = new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[3] = new WEB3GentradeCsvColumnModel(LOT_RESULT_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[4] = new WEB3GentradeCsvColumnModel(ELECTED_QUANTITY_LABEL, 4, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[5] = new WEB3GentradeCsvColumnModel(SUBSTITUTE_PRIORITY_LABEL, 5, WEB3GentradeCsvColumnModel.LONGTYPE, null);
        
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * �L�[�w�b�_������̑Ó������`�F�b�N���A���e���v���p�e�B�ɃZ�b�g����B<BR>
     * <BR>
     * �P�j�@@�L�[�w�b�_�w�蕶������<BR>
     * �@@�L�[�w�b�_.split(���I���ʃA�b�v���[�hCSV.��؂蕶��)�ɂāA��؂蕶������<BR>��token[]�ɕ�������B<BR>
     * �@@token�̗v�f����2�łȂ��ꍇ�itoken.length != 2�j�A�w�b�_�̃t�H�[�}�b�g��<BR>�Ⴄ�Ɣ��肵��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00522<BR>
     * �Q�j�@@�l�擾<BR>
     * �@@�Q�|�P�j�@@�����R�[�h�`�F�b�N<BR>
     * �@@�@@token[0]�������������R�[�h�łȂ��ꍇ<BR>
     * �@@�@@�itoken[0] != this.IPO����.IPO�����s.�����R�[�h�j�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00523<BR>
     * <BR>
     * �@@�Q�|�Q�j�@@�V�K�^���I�敪�`�F�b�N<BR>
     * �@@�@@�|token[1]���������V�K�^���I�敪�łȂ��ꍇ<BR>
     * �@@�@@�itoken[1] != ���I���ʃA�b�v���[�hCSV.�V�K�^�J�㒊�I���_�V�K���I &&<BR>
     * �@@�@@ token[1] != ���I���ʃA�b�v���[�hCSV.�V�K�^�J�㒊�I���_�J�㒊�I�j�A<BR>
     * �@@�@@��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00524<BR>
     * �@@�@@�|token[1]�������̒��I�敪�ƈ�v���Ȃ��ꍇ�A��O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01998<BR>
     * <BR>
     * �R�j�@@�L�[�w�b�_�Z�b�g<BR>
     * �@@this.set�L�[�w�b�_()�ɂăL�[�w�b�_���Z�b�g����B<BR>
     * <BR>
     * �@@[set�L�[�w�b�_()�Ɏw�肷�����]<BR>
     * �@@�L�[�w�b�_�F�@@token[]<BR>
     * <BR>
     * �S�j�@@�V�K�^�J�㒊�I��ʃZ�b�g<BR>
     * �@@this.�V�K�^�J�㒊�I��ʂ�token[1]���Z�b�g����B<BR>
     * <BR>
     * @@see String#split<BR>
     * @@param l_strKeyHeaderLine - �L�[�w�b�_�s<BR>
     * @@param l_strLotDiv ���I�敪<BR>
     * <BR>
     * "�����R�[�h,�V�K�^�J�㒊�I���ʎ��"
     * @@roseuid 40F4883D03AE
     */
    public void validateKeyHeader(String l_strKeyHeaderLine, String l_strLotDiv) throws WEB3BaseException
    {
    
        final String STR_METHOD_NAME = " validateKeyHeader(String, String)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strKeyHeaderLine == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //�P�j�@@�L�[�w�b�_�w�蕶������
        String[] l_tokens = l_strKeyHeaderLine.split(regex);
        if(l_tokens.length != 2)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00522,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //�Q�j�@@�l�擾
        //�Q�|�P�j�@@�����R�[�h�`�F�b�N
        IpoProductParams l_ipoProductParams = (IpoProductParams)this.getIpoProduct().getDataSourceObject();
        //2004/11/30 U00492 �����R�[�h�ł͂Ȃ��AIPO�����R�[�h�̎擾�ɏC���@@���@@SRA  START
//		String l_strProductCode = String.valueOf(l_ipoProductParams.getIpoProductId());
		String l_strProductCode = l_ipoProductParams.getProductCode();
		//2004/11/30 U00492 �����R�[�h�ł͂Ȃ��AIPO�����R�[�h�̎擾�ɏC���@@���@@SRA  END
        if(!l_strProductCode.equals(l_tokens[0]))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00523,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //�Q�|�Q�j�@@�V�K�^���I�敪�`�F�b�N
        if(!NEW_ADVANCE_LOT_TYPE_NEW_LOT.equals(l_tokens[1]) && !NEW_ADVANCE_LOT_TYPE_ADVANCE_LOT.equals(l_tokens[1]))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00524,
                getClass().getName() + STR_METHOD_NAME);            
            
        }
        if ((l_strLotDiv == null && l_tokens[1] != null)
            || (l_strLotDiv != null && l_tokens[1] == null) 
            || (l_strLotDiv != null && !l_strLotDiv.equals(l_tokens[1])))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01998,
                getClass().getName() + STR_METHOD_NAME);            
        }
        //�R�j�@@�L�[�w�b�_�Z�b�g
        this.setKeyHeader(l_tokens);
        //�S�j�@@�V�K�^�J�㒊�I��ʃZ�b�g
        this.newAdvanceLotType = l_tokens[1];
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (set�ڋq)<BR>
     * �ڋq�R�[�h�icheckDigit�t���j���Z�b�g�������B <BR>
     * �P�j�@@�i�ڋq�R�[�h�j�J�������f���擾 <BR>
     * �@@this.get�J�������f��()�ɂ�CSV�J�������f�����擾����B<BR> 
     * �@@[get�J�������f��()�Ɏw�肷�����] <BR>
     * �@@���ڃ��x���F�@@���I���ʃA�b�v���[�hCSV.�ڋq�R�[�h���x�� <BR>
     * <BR>
     * �Q�j�@@�l�Z�b�g <BR>
     * �@@this.set���ڒl()�ɂč��ڒl���Z�b�g����B<BR>
     * �@@[set���ڒl()�Ɏw�肷�����] <BR>
     * �@@�s�ԍ��F�@@�����̍s�ԍ� <BR>
     * �@@�J�����F�@@�P�j�Ŏ擾�����J�������f��<BR> 
     * �@@�l�F�@@�ڋq.getAccountCode() <BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - �s�ԍ����w�肷��B
     * @@param l_lngAccountId - �����h�c
     * @@roseuid XXXXXXXXXXX
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
            String l_strCode= l_mainAccount.getAccountCode();            
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode);
        }
        catch (NotFoundException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00398,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    
    /**
     * (is�V�K���I)<BR>
     * �V�K���I���𔻒肷��B<BR>
     * <BR>
     * �@@�|�ithis.�V�K�^�J�㒊�I��� == ���I���ʃA�b�v���[�h<BR>
     * CSV.�V�K�^�J�㒊�I���_�V�K���I�j�̏ꍇ�Atrue��ԋp����B<BR>
     * �@@�|�ȊO�Afalse��ԋp����B<BR>
     * @@return boolean
     * @@roseuid 40F489430005
     */
    public boolean isNewLot() 
    {

        //�V�K���I���𔻒肷��
        return NEW_ADVANCE_LOT_TYPE_NEW_LOT.equals(this.newAdvanceLotType);
        
    }
    
    /**
     * ���I���ʍ��v�l���擾����B<BR>
     * <BR>
     * this.���I���ʍ��v�l��ԋp����B
     * @@return double
     * @@roseuid 40F4A31700A1
     */
    public double getElectedQuantityTotal() 
    {

        //���I���ʍ��v�l���擾����        
        return this.electedQuantityTotal;
     
    }
    
    /**
     * �����m�萔�ʍ��v�l���擾����B<BR>
     * <BR>
     * this.�����m�萔�ʍ��v�l��ԋp����B
     * @@return double
     * @@roseuid 40F605070205
     */
    public double getAllotQuantityTotal() 
    {

        //�����m�萔�ʍ��v�l���擾����        
        return this.allotQuantityTotal;
        
    }
    
    /**
     * ���I���ʂ����v�l�ɉ��Z����B<BR>
     * <BR>
     * this.���I���ʍ��v�l = this.���I���ʍ��v�l + ���I����
     * @@param l_dblElectedQuantity - ���I����
     * @@roseuid 40F4A3340321
     */
    public void addElectedQuantity(double l_dblElectedQuantity) 
    {

        //���I���ʂ����v�l�ɉ��Z����
        this.electedQuantityTotal = this.electedQuantityTotal + l_dblElectedQuantity;
     
    }
    
    /**
     * �J��҂����ʂ��܂ށA�����m�萔�ʂ������m�萔�ʍ��v�l�ɉ��Z����B<BR>
     * <BR>
     * �P�j�@@IPO�\���̊����m�萔�ʂ��擾�B<BR>
     * �@@�� ���I�҂ōw���\���ς̏ꍇ(*1)<BR>
     * �@@�@@�|IPO�\��.IPO�\���s.�w���\�����ʁB<BR>
     * <BR>
     * �@@�� ���I�҂ōw���\�����ς̏ꍇ(*2)�@@���J��҂�����<BR>
     * �@@�@@�|IPO�\��.IPO�\���s.���I���ʁB<BR>
     * <BR>
     * �@@�� �⌇�҂ŕ⌇���I�ς̏ꍇ(*3)�A<BR>
     * �@@�@@�|IPO�\��.IPO�\���s.���I���ʁB<BR>
     * <BR>
     * �@@�� �ȊO�̏ꍇ�A0�B<BR>
     * <BR>
     * �@@(*1) ���I�҂ōw���\���ς̔���<BR>
     * �@@�@@IPO�\��.is���I��() == true && IPO�\��.is�w���\��() == true<BR>
     * �@@(*2) ���I�҂ōw���\�����ς̔���<BR>
     * �@@�@@IPO�\��.is���I��() == true && IPO�\��.is�w���\��() == false &&<BR>
     * �@@�@@�@@IPO�\��.is����() == false && IPO����.is�w���\���I���i���Аݒ�j() <BR>== false<BR>
     * �@@(*3) �⌇�҂ŕ⌇���I�̔���<BR>
     * �@@�@@IPO�\��.is�⌇��() == true && IPO�\��.IPO�\���s.���I���ʁi�J��j == <BR>1�F���I<BR>
     * <BR>
     * �Q�j�@@�����m�萔�ʉ��Z<BR>
     * <BR>
     * �@@this.�����m�萔�ʍ��v�l = this.�����m�萔�ʍ��v�l + �i�P�j�Ŏ擾����<BR>�����m�萔�ʁj<BR>
     * <BR>
     * @@param l_ipoOrder - IPO�\���I�u�W�F�N�g
     * @@param l_ipoProduct - IPO�����I�u�W�F�N�g
     * @@roseuid 40F5FF8801C7
     */
    public void addAllotQuantity(WEB3IpoOrderImpl l_ipoOrder, WEB3IpoProductImpl l_ipoProduct) 
    {
        
        final String STR_METHOD_NAME = " addAllotQuantity(WEB3IpoOrderImpl, WEB3IPOProductImpl )";
        log.entering(STR_METHOD_NAME);
        
        if (l_ipoOrder == null || l_ipoProduct == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017, this.getClass().getName() + STR_METHOD_NAME);
        }
        
        //IPO�\���̊����m�萔�ʂ��擾�B
        IpoOrderParams l_ipoOrderParams = (IpoOrderParams)l_ipoOrder.getDataSourceObject();
        //�����m�萔��
        double l_dblApplicationQuantity = 0;
        //(*1) ���I�҂ōw���\���ς̔���
        if(l_ipoOrder.isElected() && l_ipoOrder.isOffered())
        {
            
            if(l_ipoOrderParams.getApplicationQuantityIsNull())
            {
                
                l_ipoOrderParams.setApplicationQuantity(0); 
                
            }
            else
            {
                
                l_dblApplicationQuantity = l_ipoOrderParams.getApplicationQuantity();
                
            }
            
            
        }
        //(*2) ���I�҂ōw���\�����ς̔���
        else if(l_ipoOrder.isElected() && !l_ipoOrder.isOffered() 
            && !l_ipoOrder.isDecline() && !l_ipoProduct.isOfferEnd())
        {
            
            l_dblApplicationQuantity = l_ipoOrderParams.getElectedQuantity();
            
        }
        //(*3) �⌇�҂ŕ⌇���I�̔���
        else if(l_ipoOrder.isWaiting() && WEB3LotResultRetryDef.ELECTION.equals(l_ipoOrderParams.getLotResultRetry()))
        {
            
            l_dblApplicationQuantity = l_ipoOrderParams.getElectedQuantity();
            
        }
        //�ȊO�̏ꍇ�A0
        else
        {
            
            l_dblApplicationQuantity = 0;
            
        }
        //�Q�j�@@�����m�萔�ʉ��Z
        this.allotQuantityTotal = allotQuantityTotal + l_dblApplicationQuantity;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * �s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĕ��X�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.���X�R�[�h���x��)�̖߂�l�B<BR>
     * @@param l_intLineNo - �s�ԍ�
     * @@return String
     * @@roseuid 40F4FC2601FD
     */
    public String getBranchCode(int l_intLineNo) 
    {
        
        final String STR_METHOD_NAME = " getBranchCode(int)";
        log.entering(STR_METHOD_NAME);
        //�s�ԍ��ɑΉ����閾�׍s�̕��X�R�[�h���擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(BRANCH_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;
        
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂČڋq�R�[�h���擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.�ڋq�R�[�h���x��)�̖߂�l�B
     * @@param l_intLineNo - �s�ԍ�
     * @@return String
     * @@roseuid 40F505C90393
     */
    public String getAccountCode(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getAccountCode(int)";
        log.entering(STR_METHOD_NAME);
        //�s�ԍ��ɑΉ����閾�׍s�̌ڋq�R�[�h���擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ACCOUNT_CODE_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;

    }
    
    /**
     * (get���I����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̒��I���ʂ��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂĒ��I���ʂ��擾���ԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.���I���ʃ��x��)�̖߂�l�B
     * @@param l_intLineNo - �s�ԍ�
     * @@return String
     * @@roseuid 40F50600026A
     */
    public String getLotResult(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getLotResult(int)";
        log.entering(STR_METHOD_NAME);
        //�s�ԍ��ɑΉ����閾�׍s�̒��I���ʂ��擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(LOT_RESULT_LABEL);
        String l_strValue = (String)this.getValue(l_intLineNo, l_columnModel);
        
        log.exiting(STR_METHOD_NAME);
        return l_strValue;

    }
    
    /**
     * (get���I����)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̓��I���ʂ��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂē��I���ʂ��擾���A�W���f�[�^�^�idouble�j�ɕϊ����ĕԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.���I���ʃ��x��)�̖߂�l�B
     * @@param l_intLineNo - �s�ԍ�
     * @@return double
     * @@roseuid 40F5061900F3
     */
    public double getElectedQuantity(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getElectedQuantity(int)";
        log.entering(STR_METHOD_NAME);
        //�s�ԍ��ɑΉ����閾�׍s�̓��I���ʂ��擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(ELECTED_QUANTITY_LABEL);
        Double l_value = (Double)this.getValue(l_intLineNo, l_columnModel);
		// 2004/12/2 U00509 NaN�ł͂Ȃ��A0���Z�b�g����@@���@@SRA  START
        double l_dblValue = 0.0D;
//		double l_dblValue = 0.0D / 0.0D;
		if(l_value != null)
		// 2004/12/2 U00509 NaN�ł͂Ȃ��A0���Z�b�g����@@���@@SRA  END
        {
            l_dblValue = l_value.doubleValue();
        }
        return l_dblValue;

    }
    
    /**
     * (get�D�揇��)<BR>
     * �s�ԍ��ɑΉ����閾�׍s�̗D�揇�ʂ��擾����B<BR>
     * <BR>
     * this.get���ڒl()�ɂėD�揇�ʂ��擾���A�W���f�[�^�^�ilong�j�ɕϊ����ĕԋp����B<BR>
     * <BR>
     * [get���ڒl()�Ɏw�肷�����]<BR>
     * �s�ԍ��F�@@�s�ԍ�<BR>
     * �J�����F�@@get�J�������f��(���I���ʃA�b�v���[�hCSV.�D�揇�ʃ��x��)�̖߂�l�B
     * @@param l_intLineNo - �s�ԍ�
     * @@return Long
     * @@roseuid 40F506570086
     */
    public Long getSubstitutePriority(int l_intLineNo) 
    {

        final String STR_METHOD_NAME = " getSubstitutePriority(int)";
        log.entering(STR_METHOD_NAME);
        //�s�ԍ��ɑΉ����閾�׍s�̓��I���ʂ��擾����
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(SUBSTITUTE_PRIORITY_LABEL);
        Long l_lngValueL = (Long)this.getValue(l_intLineNo, l_columnModel);
        
//        long l_lngValue = 0;
//        if(l_lngValueL != null)
//        {
//            l_lngValue = l_lngValueL.longValue();
//        }

        return l_lngValueL;

    }
    
    /**
     * this.IPO������ԋp����B
     * @@return webbroker3.ipo.WEB3IpoProductImpl
     * @@roseuid 40F5070B01AF
     */
    public WEB3IpoProductImpl getIpoProduct() 
    {

        //this.IPO������ԋp����
        return this.ipoProduct;
     
    }
    
    /**
     * (validate�V�K���I���I����)<BR>
     * ���I���ʂ̒l�����������`�F�b�N���s���B<BR>
     * ���@@�V�K���I�̏ꍇ<BR>
     * <BR>
     * �ȉ��̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B<BR>
     * <BR>
     *   [Error����]<BR>
     *   �i���I���� != ���I���ʃA�b�v���[�hCSV.���I����_���I�j and<BR>
     *   �i���I���� != ���I���ʃA�b�v���[�hCSV.���I����_�⌇�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00519<BR>
     * @@param l_strLotResult - ���I����
     * @@roseuid 40F50BCB0160
     */
    public void validateNewLotLotResult(String l_strLotResult) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateNewLotLotResult(String)";
        log.entering(STR_METHOD_NAME);
        
        //�ȉ��̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����
        //Q&A:WEB3-IPO-A-FT-0058
        if(!LOT_RESULT_PRIZED.equals(l_strLotResult) && !LOT_RESULT_WAITING.equals(l_strLotResult))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00519,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate�J�㒊�I���I����)<BR>
     * ���I���ʂ̒l�����������`�F�b�N���s���B<BR>
     * ���@@�J�㒊�I�̏ꍇ<BR>
     * <BR>
     * �P�j�@@���I���ʂ̒l�`�F�b�N<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�ꍇ�A�R�[�h�l�s���Ƃ��ė�O���X���[����B<BR>
     * <BR>
     * �@@[Error����]<BR>
     * �@@�i���I���� != ���I���ʃA�b�v���[�hCSV.���I����_���I�j And<BR>
     * �@@�i���I���� != ���I���ʃA�b�v���[�hCSV.���I����_���I�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00520<BR>
     * <BR>
     * �Q�j�@@���I���ʁ^���I���ʂ̊֘A�`�F�b�N<BR>
     * �@@�ȉ��̏����ɓ��Ă͂܂�ꍇ�A�⌇���I�҂ɓ��I���ʂ��������Ă����<BR>���肵�A��O���X���[����B<BR>
     * <BR>
     * �@@[Error����]<BR>
     * �@@�i���I���� == ���I���ʃA�b�v���[�hCSV.���I����_���I�j &&<BR>
     * �@@�i���I���� != 0�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00521<BR>
     * <BR>
     * @@param l_strLotResult - ���I����
     * @@param l_dblPrizeQuantity - ���I����
     * @@roseuid 40F50DC50364
     */
    public void validateAdvanceLotLotResult(String l_strLotResult, double l_dblPrizeQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateAdvanceLotLotResult(String, double)";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@���I���ʂ̒l�`�F�b�N
        if(!LOT_RESULT_PRIZED.equals(l_strLotResult) && !LOT_RESULT_REJECTED.equals(l_strLotResult))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00520,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        //�Q�j�@@���I���ʁ^���I���ʂ̊֘A�`�F�b�N
        if(LOT_RESULT_REJECTED.equals(l_strLotResult) && l_dblPrizeQuantity != 0)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00521,
                getClass().getName() + STR_METHOD_NAME);
            
        }        
        log.exiting(STR_METHOD_NAME);
             
    }
    
    /**
     * (validate�D�揇��)<BR>
     * �D�揇�ʂ̃`�F�b�N���s���B<BR>
     * �ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * [Error����]<BR>
     * �i���I���� == ���I���ʃA�b�v���[�hCSV.���I����_���I�j &&<BR>
     * �i�D�揇�� != 0�j<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00516<BR>
     * @@param l_strLotResult - ���I����
     * @@param l_lngSubstitutePriority - �D�揇��
     * @@roseuid 40F50EBB0112
     */
    public void validateSubstitutePriority(String l_strLotResult, Long l_lngSubstitutePriority) throws WEB3BaseException
    {

        final String STR_METHOD_NAME = " validateSubstitutePriority(String, long)";
        log.entering(STR_METHOD_NAME);
        
        if(l_lngSubstitutePriority == null)
        {
            l_lngSubstitutePriority = new Long(0);
        }
        
        //�D�揇�ʂ̃`�F�b�N���s��
        if(LOT_RESULT_PRIZED.equals(l_strLotResult) && !l_lngSubstitutePriority.equals(new Long(0)))
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00516,
                getClass().getName() + STR_METHOD_NAME);
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate�d���ڋq)<BR>
     * �d���ڋq���ǉ�����Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * get�ڋq�R�[�h(�ԍ�)<BR>
     * get���X�R�[�h(�ԍ�)<BR>
     * �ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����B<BR>
     * <BR>
     * �擾�������X�R�[�h+�ڋq�R�[�h�Ǝw��s�ԍ����<BR>
     * �O�̖��׍s�̕��X�R�[�h+�ڋq�R�[�h���r���A<BR>
     * �����l�����݂���ꍇ�́A��O���X���[����B<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00517<BR>
     * @@param l_intLineNo - �s�ԍ�
     * @@roseuid 40F512FA0374
     */
    public void validateDuplicateAccount(int l_intLineNo) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateRepeatAccount(int)";
        log.entering(STR_METHOD_NAME);
        
        //get�ڋq�R�[�h(�ԍ�)
        //get���X�R�[�h(�ԍ�)
        //�ɂāA�w��s�ԍ��̌ڋq�R�[�h���擾����
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        String l_strBranchCode = this.getBranchCode(l_intLineNo);
        String l_strCompareCode = l_strBranchCode + l_strAccountCode;
        for(int i = 0; i < l_intLineNo; i++)
        {
            
            String l_strAccCode = getAccountCode(i);
            String l_strBraCode = getBranchCode(i);
            String l_strComCode = l_strBraCode + l_strAccCode;
            if(l_strCompareCode.equals(l_strComCode))
            {
                
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00517,
                    getClass().getName() + STR_METHOD_NAME);
                
            }
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (validate���I����)<BR>
     * ���I���ʂ��\�����ʂ𒴂��Ă��Ȃ����`�F�b�N���s���B<BR>
     * <BR>
     * �ȉ��̏����ɓ��Ă͂܂�ꍇ�A��O���X���[����B<BR>
     * <BR>
     * [Error����]<BR>
     * �\������ < ���I���� <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00518<BR>
     * @@param l_dblElectedQuantity - ���I����
     * @@param l_dblOrderQuantity - �\������
     * @@roseuid 40F518310038
     */
    public void validateElectedQuantity(double l_dblElectedQuantity, double l_dblOrderQuantity) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateElectedQuantity(double, double)";
        log.entering(STR_METHOD_NAME);
        
        if(l_dblOrderQuantity < l_dblElectedQuantity)
        {
            
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00518,
                getClass().getName() + STR_METHOD_NAME);            
            
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }

    /**
     * (validate���ڃ����O�X)<BR>
     * �A�b�v���[�h�t�@@�C�����̍��ڒl��DB�����O�X�͈͓̔��ł��邩���`�F�b�N����B<BR>
     * <BR>
     * �A�b�v���[�h�t�@@�C�����̈ȉ��̍��ڂ�getter���\�b�h�Ŏ擾���A<BR>
     * ���ڒ����ȉ��Ɏ����ő�l�𒴂��Ă��Ȃ����Ƃ��`�F�b�N����B <BR>
     * �����Ă���ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01742<BR>
     * <BR>
     * �@@[���ڍő咷] <BR>
     * �@@�ڋq���F�@@40byte <BR>
     * �@@���I���ʁF�@@1byte <BR>
     * �@@���I���ʁF�@@9byte <BR>
     * �@@�D�揇�ʁF�@@7byte <BR>
     * <BR>
     * �A�b�v���[�h�t�@@�C�����̈ȉ��̍��ڂ�getter���\�b�h�Ŏ擾���A<BR>
     * ���ڒ����ȉ��Ɏ��������O�X�ł��邩���`�F�b�N����B <BR>
     * �����O�X���Ⴄ�ꍇ�A��O���X���[����B <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01994<BR>
     * <BR>
     * �@@[���ڒ�] <BR>
     * �@@���X�R�[�h�F�@@3byte <BR>
     * �@@�ڋq�R�[�h�F�@@6byte <BR>
     * <BR>
     * @@param l_intLineNo - �s�ԍ�
     * <BR>
     */
    public void validateItemLength(int l_intLineNo) throws WEB3BaseException
    {
        
        final String STR_METHOD_NAME = " validateItemLength(int)";
        log.entering(STR_METHOD_NAME);
        
        //getter���\�b�h�Ŏw��s�ԍ��̊e�U���ڂ��擾
        ////�@@���X�R�[�h
        String l_strBranchCode  = this.getBranchCode(l_intLineNo);
        ////�A�ڋq�R�[�h
        String l_strAccountCode = this.getAccountCode(l_intLineNo);
        ////�B�ڋq��  >>>>>>�J�������f���擾�B�s�ԍ��ɑΉ����閾�׍s�̌ڋq�����擾����
        WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);
        String l_strAccountName = (String)this.getValue(l_intLineNo, l_csvDataModelAccountName);
        ////�C���I����
        String l_strLotResult   = this.getLotResult(l_intLineNo);
        ////�D���I����
        String l_strElectedQuantity =WEB3StringTypeUtility.formatNumber(this.getElectedQuantity(l_intLineNo));
        ////�E�D�揇��
        Long l_LngSubstitutePriority = this.getSubstitutePriority(l_intLineNo);
        String l_strSubstitutePriority = null;
        
        if(l_LngSubstitutePriority != null)
        {
            l_strSubstitutePriority = l_LngSubstitutePriority.toString();
        }
        else
        {
            
        }
        
        
        //�@@�`�E�ɂ��āAnull�łȂ��������O�X�`�F�b�N���s��
        if((l_strAccountName != null && l_strAccountName.length() > 40)
            || (l_strLotResult != null && l_strLotResult.length() > 1)
            || (l_strElectedQuantity != null && l_strElectedQuantity.length() > 9)
            || (l_strSubstitutePriority != null && l_strSubstitutePriority.length() > 7))
        {
            log.exiting(STR_METHOD_NAME);
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_01742,
                 getClass().getName() + STR_METHOD_NAME);
        }

        if ((l_strBranchCode != null && l_strBranchCode.length() != 3)
            || (l_strAccountCode != null && l_strAccountCode.length() != 6))
        {
            log.debug("�����O�X���Ⴄ�ꍇ�A��O���X���[����B");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01994,
                getClass().getName() + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * (setDataFrom�A�b�v���[�hTemp)<BR>
     * �P�j�@@super.setDataFrom�A�b�v���[�hTemp()���R�[������B<BR>
     * �Q�j�@@this.�V�K�^�J�㒊�I��ʂɁAthis.�J�����w�b�_[1]���Z�b�g����B<BR>
     * @@param l_lngUploadId - �A�b�v���[�h�h�c<BR>
     * @@throws WEB3SystemLayerException
     */
    public void setDataFromUploadTemp(long l_lngUploadId)
            throws WEB3SystemLayerException
    {

        final String STR_METHOD_NAME = " setDataFromUploadTemp(long)";
        log.entering(STR_METHOD_NAME);
        super.setDataFromUploadTemp(l_lngUploadId);

        this.newAdvanceLotType = this.keyHeader[1];
        
        
    }
    
    /**
     * (save�A�b�v���[�h�J�n)<BR>
     * super.save�A�b�v���[�h�J�n()���R�[������B<BR>
     * <BR>
     * save�A�b�v���[�h�J�n()�Ɏw�肷�����] <BR>
     * �f�[�^�L�[�F�@@this.IPO����.getIPO�����h�c() <BR>
     * ���l�P�F�@@���I��ʋ敪 <BR>
     * ���l�Q�F�@@null<BR>
     * ���l�R�F�@@null<BR>
     * �X�V�҃R�[�h�F�@@�Ǘ��҃R�[�h<BR>
     * <BR>
     * @@param  �X�V�҃R�[�h 
     * @@param  ���I�敪
     * @@author sra523
     *
     */
    public void saveUpLoadStart(String l_strAdministratorCode,String l_strLotDiv)
            throws WEB3SystemLayerException
    {
        final String STR_METHOD_NAME = " saveUpLoadStart(String l_strAdministratorCode)";
        log.entering(STR_METHOD_NAME);
        super.saveUpLoadStart(
                this.getIpoProduct().getProductId(),
                l_strLotDiv,
                null,
                null,
                l_strAdministratorCode);
                    
    }
}
@
