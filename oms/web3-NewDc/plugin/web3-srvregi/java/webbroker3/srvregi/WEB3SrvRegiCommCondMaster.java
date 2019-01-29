head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.38.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiCommCondMaster.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �T�[�r�X���p�萔�������}�X�^�[(WEB3SrvRegiCommCondMaster.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 ���w�� (���u) �V�K�쐬
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BusinessObject;

import webbroker3.srvregi.data.SrvRegiCommCondMasterParams;
import webbroker3.util.WEB3LogUtility;

/**
 * (�T�[�r�X���p�萔�������}�X�^�[)<BR>
 * �T�[�r�X���p�萔�������}�X�^�[�N���X<BR>        
 *             
 * @@author ���w��
 * @@version 1.0
 */
public class WEB3SrvRegiCommCondMaster implements BusinessObject
{
    
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3SrvRegiCommCondMaster.class);
        
    /**
     * (�T�[�r�X���p�萔�������}�X�^�[�s)<BR>
     */
    private SrvRegiCommCondMasterParams srvRegiCommCondMasterParams;

    /**
     * (�T�[�r�X���p�萔�������}�X�^�[)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@param l_strInstitutionCode - (�،���ЃR�[�h)<BR>
     * @@param l_strOrderAccProduct - (������t���i)<BR>
     * @@param l_strCommProdTypeName - (���i��)<BR>
     * @@roseuid 4186E4B0025E
     */
    protected WEB3SrvRegiCommCondMaster(String l_strInstitutionCode, String l_strOrderAccProduct, String l_strCommProdTypeName) 
    {
        final String STR_METHOD_NAME = " WEB3SrvRegiCommCondMaster(String, String, String) ";
        log.entering(STR_METHOD_NAME);
        
        this.srvRegiCommCondMasterParams = new SrvRegiCommCondMasterParams();
        this.srvRegiCommCondMasterParams.setInstitutionCode(l_strInstitutionCode);
        this.srvRegiCommCondMasterParams.setOrderAccProduct(l_strOrderAccProduct);
        this.srvRegiCommCondMasterParams.setCommProdTypeName(l_strCommProdTypeName);
        
        log.exiting(STR_METHOD_NAME);    
    }
    
    /**
     *�igetDataSourceObject�̎����j<BR>
     * <BR>
     * this.�T�[�r�X���p�萔�������}�X�^�[�s��ԋp����B<BR>
     * @@return Object
     * @@roseuid 4186E477028D
     */
    public Object getDataSourceObject() 
    {
        final String STR_METHOD_NAME = " getDataSourceObject() ";
        log.entering(STR_METHOD_NAME);
        
        log.exiting(STR_METHOD_NAME);  
        return this.srvRegiCommCondMasterParams;
    }
    
    /**
     * (get������t���i)<BR>
     * this.�T�[�r�X���p�萔�������}�X�^�[�s.get������t���i( )�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 4186E4DE01C2
     */
    public String getOrderAccProduct() 
    {
        final String STR_METHOD_NAME = " getOrderAccProduct() ";
        log.entering(STR_METHOD_NAME);
        
        String l_strOrderAccProduct = this.srvRegiCommCondMasterParams.getOrderAccProduct();
        
        log.exiting(STR_METHOD_NAME);  
        return l_strOrderAccProduct;
    }
    
    /**
     * (get���i��)<BR>
     * this.�T�[�r�X���p�萔�������}�X�^�[�s.get���i��( )�̖߂�l��ԋp����B<BR>
     * @@return String
     * @@roseuid 4186E4C7001C
     */
    public String getCommProdTypeName() 
    {
        final String STR_METHOD_NAME = " getCommProdTypeName() ";
        log.entering(STR_METHOD_NAME);
        
        String l_strCommProdTypeName = this.srvRegiCommCondMasterParams.getCommProdTypeName();
        
        log.exiting(STR_METHOD_NAME); 
        return l_strCommProdTypeName;
    }
}
@
