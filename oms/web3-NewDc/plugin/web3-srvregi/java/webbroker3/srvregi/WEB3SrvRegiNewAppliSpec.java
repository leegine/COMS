head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.56;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiNewAppliSpec.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�V�K�\�����e(WEB3SrvRegiNewAppliSpec.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 �A�C��(���u) �V�K�쐬
*/

package webbroker3.srvregi;

import java.sql.Timestamp;

import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�V�K�\�����e)<BR>
 * �T�[�r�X���p�\�����e�N���X <BR>
 * �i�Ǘ��Ҍڋq�o�^�p�f�[�^�N���X�j<BR>
 * �Ǘ��Ҍڋq�f�[�^�A�b�v���[�h�^�Ǘ��Ҍڋq�o�^�ɂĎg�p<BR>
 *  
 * @@author �A�C��
 * @@version 1.0  
 */
public class WEB3SrvRegiNewAppliSpec 
{
    
    /**
     * (���O���[�e�B���e�B)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3SrvRegiNewAppliSpec.class);
    
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
     * (�o����)<BR>
     */
    private Timestamp paymentDate;
    
    /**
     * �i�\�����I�敪�j<BR>
     */
    private String appliLotDiv;
    
    /**
     * @@roseuid 416F4FED0280
     */
    public WEB3SrvRegiNewAppliSpec() 
    {
     
    }
    
    /**
     * (create�T�[�r�X���p�V�K�\�����e)<BR>
     * �istatic���\�b�h�j <BR>
     * <BR>
     * 1) �T�[�r�X���p�V�K�\�����e�I�u�W�F�N�g�𐶐�����B<BR>
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
     * 6) this.set�K�p�J�n��( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�K�p�J�n��<BR>
     * 7) this.set�K�p�I����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�K�p�I����<BR>
     * 8) this.set�\����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�\����<BR>
     * 9) this.set�o�^�敪( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�o�^�敪<BR>
     * 10) this.set���p����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.���p����<BR>
     * 11) this.set�o����( )���R�[������B<BR>
     * �@@[����]<BR>
     * �@@�@@����.�o����<BR>
     * <BR>
     * 12) �쐬�����T�[�r�X���p�V�K�\�����e�I�u�W�F�N�g��ԋp����B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * 0:�L���@@1:�����@@2:�S��<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@param l_strAppliLotDiv - �i�\�����I�敪�j<BR>
     * @@return webbroker3.srvregi.WEB3SrvRegiNewAppliSpec
     * @@roseuid 4110A3F7039E
     */
    public static WEB3SrvRegiNewAppliSpec createSrvRegiNewAppliSpec(
        String l_strInstitutionCode, 
        String l_strSrvDiv, 
        String l_strBranchCode, 
        String l_strAccountCode, 
        Timestamp l_tsAppliStartDate, 
        Timestamp l_tsAppliEndDate, 
        Timestamp l_tsAppliDate, 
        String l_strPaymentDiv, 
        Double l_dblUseAmt, 
        Timestamp l_tsPaymentDate,
		String l_strAppliLotDiv) 
    {
        final String STR_METHOD_NAME = 
            "createSrvRegiNewAppliSpec(" 
            + "String , String , String , String , Timestamp , Timestamp , Timestamp , String , Double , Timestamp, String" 
            + ")";
        log.entering(STR_METHOD_NAME);

        //�T�[�r�X���p�V�K�\�����e�I�u�W�F�N�g�𐶐�����
        WEB3SrvRegiNewAppliSpec l_spec = new WEB3SrvRegiNewAppliSpec();
        
        //2�،���ЃR�[�h
        l_spec.setInstitutionCode(l_strInstitutionCode);
        
        //3�T�[�r�X�敪
        l_spec.setSrvDiv(l_strSrvDiv);
        
        //4���X�R�[�h
        l_spec.setBranchCode(l_strBranchCode);
        
        //5�ڋq�R�[�h
        l_spec.setAccountCode(l_strAccountCode);
        
        //6�K�p�J�n��
        l_spec.setAppliStartDate(l_tsAppliStartDate);
        
        //7�K�p�I����
        l_spec.setAppliEndDate(l_tsAppliEndDate);
        
        //8�\����
        l_spec.setAppliDate(l_tsAppliDate);
        
        //9�o�^�敪
        l_spec.setPaymentDiv(l_strPaymentDiv);
        
        //10���p����
        l_spec.setUseAmt(l_dblUseAmt);
        
        //11�o����
        l_spec.setPaymentDate(l_tsPaymentDate);
        
        //12�\�����I�敪
        l_spec.setAppliLotDiv(l_strAppliLotDiv);

        log.exiting(STR_METHOD_NAME);
        return l_spec;
    }
    
    /**
     * (get�،���ЃR�[�h)<BR>
     * this.�،���ЃR�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41109DB40033
     */
    public String getInstitutionCode() 
    {
        return this.institutionCode;
    }
    
    /**
     * (set�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h�̐ݒ���s���B<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@roseuid 41109DBA00C0
     */
    public void setInstitutionCode(String l_strInstitutionCode) 
    {
        this.institutionCode = l_strInstitutionCode;
    }
    
    /**
     * (get�T�[�r�X�敪)<BR>
     * this.�T�[�r�X�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 41109DC20311
     */
    public String getSrvDiv() 
    {
        return this.srvDiv;
    }
    
    /**
     * (set�T�[�r�X�敪)<BR>
     * �T�[�r�X�敪�̐ݒ���s���B<BR>
     * @@param l_strSrvDiv - (�T�[�r�X�敪)<BR>
     * @@roseuid 41109DC20312
     */
    public void setSrvDiv(String l_strSrvDiv) 
    {
        this.srvDiv = l_strSrvDiv;
    }
    
    /**
     * (get���X�R�[�h)<BR>
     * this.���X�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41109DC303AE
     */
    public String getBranchCode() 
    {
        return this.branchCode;
    }
    
    /**
     * (set���X�R�[�h)<BR>
     * ���X�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strBranchCode - (���X�R�[�h)<BR>
     * @@roseuid 41109DC303AF
     */
    public void setBranchCode(String l_strBranchCode) 
    {
        this.branchCode = l_strBranchCode;
    }
    
    /**
     * (get�ڋq�R�[�h)<BR>
     * this.�ڋq�R�[�h��ԋp����B<BR>
     * @@return String
     * @@roseuid 41109DC50062
     */
    public String getAccountCode() 
    {
        return this.accountCode;
    }
    
    /**
     * (set�ڋq�R�[�h)<BR>
     * �ڋq�R�[�h�̐ݒ���s���B<BR>
     * @@param l_strAccountCode - (�ڋq�R�[�h)<BR>
     * @@roseuid 41109DC50063
     */
    public void setAccountCode(String l_strAccountCode) 
    {
        this.accountCode = l_strAccountCode;
    }
    
    /**
     * (get�K�p�J�n��)<BR>
     * this.�K�p�J�n����ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 41109DC600FE
     */
    public Timestamp getAppliStartDate() 
    {
        return this.appliStartDate;
    }
    
    /**
     * (set�K�p�J�n��)<BR>
     * �K�p�J�n���̐ݒ���s���B<BR>
     * @@param l_tsAppliStartDate - (�K�p�J�n��)<BR>
     * @@roseuid 41109DC6012D
     */
    public void setAppliStartDate(Timestamp l_tsAppliStartDate) 
    {
        this.appliStartDate = l_tsAppliStartDate;
    }
    
    /**
     * (get�K�p�I����)<BR>
     * this.�K�p�I������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 41109EE20217
     */
    public Timestamp getAppliEndDate() 
    {
        return this.appliEndDate;
    }
    
    /**
     * (set�K�p�I����)<BR>
     * �K�p�I�����̐ݒ���s���B<BR>
     * @@param l_tsAppliEndDate - (�K�p�I����)<BR>
     * @@roseuid 41109EE20227
     */
    public void setAppliEndDate(Timestamp l_tsAppliEndDate) 
    {
        this.appliEndDate = l_tsAppliEndDate;
    }
    
    /**
     * (get�\����)<BR>
     * this.�\������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 41109F17037F
     */
    public Timestamp getAppliDate() 
    {
        return this.appliDate;
    }
    
    /**
     * (set�\����)<BR>
     * �\�����̐ݒ���s���B<BR>
     * @@param l_tsAppliDate - (�\����)<BR>
     * @@roseuid 41109F17038E
     */
    public void setAppliDate(Timestamp l_tsAppliDate) 
    {
        this.appliDate = l_tsAppliDate;
    }
    
    /**
     * (get�o�^�敪)<BR>
     * this.�o�^�敪��ԋp����B<BR>
     * @@return String
     * @@roseuid 41109F3D01F8
     */
    public String getPaymentDiv() 
    {
        return this.paymentDiv;
    }
    
    /**
     * (set�o�^�敪)<BR>
     * �o�^�敪�̐ݒ���s���B<BR>
     * @@param l_strPaymentDiv - (�o�^�敪)<BR>
     * @@roseuid 41109F3D0208
     */
    public void setPaymentDiv(String l_strPaymentDiv) 
    {
        this.paymentDiv = l_strPaymentDiv;
    }
    
    /**
     * (get���p����)<BR>
     * this.���p������ԋp����B<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils.Double
     * @@roseuid 41109F3E02B4
     */
    public Double getUseAmt() 
    {
        return this.useAmt;
    }
    
    /**
     * (set���p����)<BR>
     * ���p�����̐ݒ���s���B<BR>
     * @@param l_dblUseAmt - (���p����)<BR>
     * @@roseuid 41109F3E02B5
     */
    public void setUseAmt(Double l_dblUseAmt) 
    {
       this.useAmt = l_dblUseAmt;
    }
    
    /**
     * (get�o����)<BR>
     * this.�o������ԋp����B<BR>
     * @@return Timestamp
     * @@roseuid 411745880236
     */
    public Timestamp getPaymentDate() 
    {
        return this.paymentDate;
    }
    
    /**
     * (set�o����)<BR>
     * �o�����̐ݒ���s���B<BR>
     * @@param l_tsPaymentDate - (�o����)<BR>
     * @@roseuid 411745880255
     */
    public void setPaymentDate(Timestamp l_tsPaymentDate) 
    {
       this.paymentDate = l_tsPaymentDate;
    }

    /**
     * (get�\�����I�敪)<BR>
     * this.�\�����I�敪��ԋp����B<BR>
     * @@return
     */
    public String getAppliLotDiv()
    {
        return this.appliLotDiv;
    }

    /**
     * (set�\�����I�敪)<BR>
     * �\�����I�敪�̐ݒ���s���B<BR>
     * @@param l_strAppliLotDiv - �i�\�����I�敪�j<BR>
     */
    public void setAppliLotDiv(String l_strAppliLotDiv)
    {
        appliLotDiv = l_strAppliLotDiv;
    }

}
@
