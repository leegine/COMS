head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.39.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondApplyBuyInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ������/���t���̓��N�G�X�g(WEB3BondApplyBuyInputRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/05 ������ (���u) �V�K�쐬
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondDealDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (������/���t���̓��N�G�X�g)<BR>
 * ������/���t���̓��N�G�X�g<BR>
 * <BR>
 * @@author ������
 * @@version 1.0
 */
public class WEB3BondApplyBuyInputRequest extends WEB3GenRequest 
{
    /**
     *�@@���O���[�e�B���e�B<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondApplyBuyInputRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_applyBuyInput";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609051106L; 
    
    /**
     * (����敪)<BR>
     * ����敪<BR>
     * <BR>
     * 1�F����<BR>
     * 2�F���t<BR>
     */
    public String tradeDiv;
    
    /**
     * (����ID)<BR>
     * ����ID<BR>
     */
    public String productId;
    
    /**
     * @@roseuid 44FBFD3900CB
     */
    public WEB3BondApplyBuyInputRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐��������`�F�b�N����B<BR> 
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     * <BR>
     * �P�j�@@����敪�`�F�b�N<BR>
     * �@@�P�|�P�j�@@����敪 == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00601<BR>
     * �@@�P�|�Q�j�@@����敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@1�F ����<BR>
     * �@@�@@�@@�@@�@@�@@�@@�@@2�F ���t<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_00602<BR>
     * <BR>
     * �Q�j�@@����ID�`�F�b�N<BR> 
     * �@@�@@�@@����ID == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class:�@@WEB3BusinessLayerException<BR>
     *�@@�@@tag:�@@�@@BUSINESS_ERROR_02229 <BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44BF46E8031C
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //�P�j�@@����敪�`�F�b�N 
        //�@@�P�|�P�j�@@����敪 == null�̏ꍇ�A��O���X���[����B
        if (this.tradeDiv == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00601,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����敪�����w��ł��B");            
        }
        
        //�@@�P�|�Q�j�@@����敪���ȉ��̒l�łȂ��ꍇ�A��O���X���[����B 
        //�@@�@@�@@�@@�@@�@@�@@�@@1�F ���� 
        //�@@�@@�@@�@@�@@�@@�@@�@@2�F ���t 
        else if (!(WEB3BondDealDivDef.RECRUIT.equals(this.tradeDiv)
            || WEB3BondDealDivDef.BUY.equals(this.tradeDiv)))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00602,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����敪�����݂��Ȃ��R�[�h�l�ł��B");
        }
 
        //�Q�j�@@����ID�`�F�b�N  
        //�@@�@@�@@����ID == null�̏ꍇ�A��O���X���[����B
        if (this.productId == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "����ID�����w��ł��B");
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondApplyBuyInputResponse(this);
    }
}
@
