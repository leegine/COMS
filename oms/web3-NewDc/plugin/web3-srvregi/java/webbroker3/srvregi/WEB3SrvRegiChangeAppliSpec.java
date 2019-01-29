head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.39.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiChangeAppliSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�ύX�\�����e(WEB3SrvRegiChangeAppliSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�ύX�\�����e)<BR>
 * �T�[�r�X���p�\�����e�N���X <BR>
 * �i�Ǘ��Ҍڋq�ύX�p�f�[�^�N���X�j<BR>
 * �Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�^�Ǘ��Ҍڋq�o�^�ɂĎg�p<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiChangeAppliSpec 
{

    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiChangeAppliSpec.class);

    /**
     * (�\���o�^ID)<BR>
     */
    private long registId;
    
    /**
     * (�،���ЃR�[�h)<BR>
     */
    private String institutionCode;
    
    /**
     * (�T�[�r�X�敪)<BR>
     */
    private String srvDiv;
    
    /**
     * (���X�R�[�h)<BR>
     */
    private String branchCode;
    
    /**
     * (�ڋq�R�[�h)<BR>
     */
    private String accountCode;
    
    /**
     * (�K�p�J�n��)<BR>
     */
    private Timestamp appliStartDate;
    
    /**
     * (�K�p�I����)<BR>
     */
    private Timestamp appliEndDate;
    
    /**
     * (�\�����I�敪)<BR>
     * 0:���p�@@1:�\���@@2:���I�^�{�\���@@3:���I�@@4:����@@5:�������I�@@6:�S��<BR>
     */
    private String appliLotDiv;
    
    /**
     * (�\����)<BR>
     */
    private Timestamp appliDate;
    
    /**
     * (�o�^�敪)<BR>
     * 0:�L���@@1:����<BR>
     */
    private String paymentDiv;
    
    /**
     * (���p����)<BR>
     */
    private Double useAmt;
    
    /**
     * @@roseuid 416F4FEC035B
     */
    public WEB3SrvRegiChangeAppliSpec() 
    {
     
    }
    
    /**
     * (create�T�[�r�X���p�ύX�\�����e)<BR>
     * �istatic���\�b�h�j <BR>
     * �T�[�r�X���p�ύX�\�����e�I�u�W�F�N�g�𐶐����ԋp����B <BR>
     * <BR>
     * 1) ���N���X�̃C���X�^���X�𐶐�����B<BR>
     * <BR>
     * 2) this.set�،���ЃR�[�h( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�،���ЃR�[�h<BR>
     * 3) this.set�T�[�r�X�敪( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�T�[�r�X�敪<BR>
     * 4) this.set���X�R�[�h( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.���X�R�[�h<BR>
     * 5) this.set�ڋq�R�[�h( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�ڋq�R�[�h<BR>
     * 6)this.set�\���o�^ID( )���R�[������B<BR>
     *     [����]<BR>
     *     ����.�\���o�^ID <BR>
     * 7) this.set�K�p�J�n��( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�K�p�J�n��<BR>
     * 8) this.set�K�p�I����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�K�p�I����<BR>
     * 9) this.set�\�����I�敪( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�\�����I�敪<BR>
     * 10) this.set�\����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�\����<BR>
     * 11) this.set�o�^�敪( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�o�^�敪<BR>
     * 12) this.set���p����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.���p����<BR>
     * <BR>
     * 13) �쐬�����T�[�r�X���p�ύX�\�����e�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_lngRegistId - (�\���o�^ID)<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_strAppliLotDiv - 1:���I�^�{�\���@@2:���I<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * 0:�L���@@1:�����@@2:�S��<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiChangeAppliSpec
     * @@roseuid 4110A4880091
     */
    public static WEB3SrvRegiChangeAppliSpec createSrvRegiChangeAppliSpec(
        long l_lngRegistId, 
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        String l_strAppliLotDiv, 
        Timestamp l_tsAppliDate, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt) 
    {
        final String STR_METHOD_NAME = 
            "createSrvRegiChangeAppliSpec(" +
            "long , String , String , String , String , Timestamp , Timestamp , " +
            "String , Timestamp , String l_strPaymentDiv, Double" +
            ")";
        log.entering(STR_METHOD_NAME);

        //1���N���X�̃C���X�^���X�𐶐�����
        WEB3SrvRegiChangeAppliSpec l_spec = new WEB3SrvRegiChangeAppliSpec();
        
        //2 �،���ЃR�[�h
        l_spec.setInstitutionCode(l_strInstitutionCode);
        
        //3 �T�[�r�X�敪
        l_spec.setSrvDiv(l_strSrvDiv);
        
        //4 ���X�R�[�h
        l_spec.setBranchCode(l_strBranchCode);
        
        //5 �ڋq�R�[�h
        l_spec.setAccountCode(l_strAccountCode);
        
        //6 �\���o�^ID
        l_spec.setRegistId(String.valueOf(l_lngRegistId));
        
        //7 �K�p�J�n��
        l_spec.setAppliStartDate(l_tsAppliStartDate);
        
        //8 �K�p�I����
        l_spec.setAppliEndDate(l_tsAppliEndDate);
        
        //9 �\�����I�敪
        l_spec.setAppliLotDiv(l_strAppliLotDiv);
        
        //10 �\����
        l_spec.setAppliDate(l_tsAppliDate);
        
        //11 �o�^�敪
        l_spec.setPaymentDiv(l_strPaymentDiv);
        
        //12 ���p����
        l_spec.setUseAmt(l_dblUseAmt);
        
        log.exiting(STR_METHOD_NAME);
        return l_spec;
    }
    
    /**
     * (get�\���o�^ID)<BR>
     * this.�\���o�^ID��ԋp����B<BR>
     * @@return String
     * @@roseuid 41203EC50358
     */
    public String getRegistId() 
    {
        return String.valueOf(this.registId);
    }
    
    /**
     * (set�\���o�^ID)<BR>
     * �\���o�^ID�̐ݒ���s���B<BR>
     * @@param l_strRegistId - (�\���o�^ID)<BR>
     * @@roseuid 41203EC50368
     */
    public void setRegistId(String l_strRegistId) 
    {
        this.registId = Long.parseLong(l_strRegistId);
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4110A3480091
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�̐ݒ���s���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@roseuid 4110A34800B0
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * this.�T�[�r�X�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 4110A34800C0
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (set�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�̐ݒ���s���B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@roseuid 4110A34800DF
     */
    public void setSrvDiv(String l_strSrvDiv) 
    {
        this.srvDiv = l_strSrvDiv;
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * this.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4110A34800EF
     */
    public String getBranchCode() 
    {
        return this.branchCode;
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@roseuid 4110A348010E
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.branchCode = l_strBranchCode;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * this.�ڋq�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 4110A348012D
     */
    public String getAccountCode() 
    {
        return this.accountCode;
    }
    
    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@roseuid 4110A348014C
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.accountCode = l_strAccountCode;
    }
    
    /**
     * (get�K�p�J�n��)<BR>
     * this.�K�p�J�n����ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 4110A348015C
     */
    public Timestamp getAppliStartDate() 
    {
        return this.appliStartDate;
    }
    
    /**
     * (set�K�p�J�n��)<BR>
     * �K�p�J�n���̐ݒ���s���B<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@roseuid 4110A348017B
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        this.appliStartDate = l_tsAppliStartDate;
    }
    
    /**
     * (get�K�p�I����)<BR>
     * this.�K�p�I������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 4110A348019A
     */
    public Timestamp getAppliEndDate() 
    {
        return this.appliEndDate;
    }
    
    /**
     * (set�K�p�I����)<BR>
     * �K�p�I�����̐ݒ���s���B<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@roseuid 4110A34801AA
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        this.appliEndDate = l_tsAppliEndDate;
    }
    
    /**
     * (get�\����)<BR>
     * this.�\������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 4110A34801C9
     */
    public Timestamp getAppliDate() 
    {
        return this.appliDate;
    }
    
    /**
     * (set�\����)<BR>
     * �\�����̐ݒ���s���B<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@roseuid 4110A34801D9
     */
    public void setAppliDate(Timestamp l_tsAppliDate) 
    {
        this.appliDate = l_tsAppliDate;
    }
    
    /**
     * (get�\�����I�敪)<BR>
     * this.�\�����I�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 4110A34801F8
     */
    public String getAppliLotDiv() 
    {
        return this.appliLotDiv;
    }
    
    /**
     * (set�\�����I�敪)<BR>
     * �\�����I�敪�̐ݒ���s���B<BR>
     * @@param l_strAppliLotDiv - (�\�����I�敪)<BR>
     * @@roseuid 4110A3480237
     */
    public void setAppliLotDiv(String l_strAppliLotDiv) 
    {
        this.appliLotDiv = l_strAppliLotDiv;
    }
    
    /**
     * (get�o�^�敪)<BR>
     * this.�o�^�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 411375F20121
     */
    public String getPaymentDiv() 
    {
        return this.paymentDiv;
    }
    
    /**
     * (set�o�^�敪)<BR>
     * �o�^�敪�̐ݒ���s���B<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * 0:�L���@@1:����<BR>
     * @@roseuid 411375F2016F
     */
    public void setPaymentDiv(String l_strPaymentDiv) 
    {
        this.paymentDiv = l_strPaymentDiv;
    }
    
    /**
     * (get���p����)<BR>
     * this.���p������ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.Double
     * @@roseuid 411375F2025A
     */
    public Double getUseAmt() 
    {
        return this.useAmt;
    }
    
    /**
     * (set���p����)<BR>
     * ���p�����̐ݒ���s���B<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@roseuid 411375F202E6
     */
    public void setUseAmt(Double l_dblUseAmt) 
    {
        this.useAmt = l_dblUseAmt;
    }
}
@
