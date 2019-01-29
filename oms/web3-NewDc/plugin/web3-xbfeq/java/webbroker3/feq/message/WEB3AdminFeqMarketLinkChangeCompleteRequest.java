head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.34.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqMarketLinkChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������s��A���敪�ύX�������N�G�X�g(WEB3AdminFeqMarketLinkChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/12 �����q (���u) �V�K�쐬
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (�Ǘ��ҊO�������s��A���敪�ύX�������N�G�X�g)<BR>
 * �Ǘ��ҊO�������s��A���敪�ύX�������N�G�X�g�N���X<BR>
 * 
 * @@author �����q(���u)
 * @@version 1.0
 */

public class WEB3AdminFeqMarketLinkChangeCompleteRequest extends WEB3GenRequest
{    
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_market_link_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200609121855L;  
    
    /**
     * @@roseuid 42CE39FD00AB
     */
    public WEB3AdminFeqMarketLinkChangeCompleteRequest() 
    {
     
    }
    
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminFeqMarketLinkChangeConfirmRequest.class);
    
    /**
     * (�O�������s��A���󋵈ꗗ)<BR>
     * �O�������s��A���󋵂̔z��<BR>
     */
    public WEB3FeqMarketLinkStateUnit[] feqMarketLinkStateList;
    
    /**
     * (�Ïؔԍ�)<BR>
     * �Ïؔԍ�<BR>
     */
    public String password;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR> 
     *�i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR> 
     *<BR>
     * �P�j�O�������s��A���󋵈ꗗ�`�F�b�N<BR> 
     *�@@�P�|�P�jthis.�O�������s��A���󋵈ꗗ == null�̏ꍇ�A��O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_02650<BR> 
     *<BR>
     *�@@�P�|�Q�jthis.�O�������s��A���󋵈ꗗ�̌������A�ȉ��̏������J��Ԃ��B<BR> 
     *�@@�@@�P�|�Q�|�P�j�O�������s��A����.validate()���R�[������B <BR>
     *<BR>
     * �Q�j�Ïؔԍ��`�F�b�N<BR> 
     *�@@this.�Ïؔԍ� == null�̏ꍇ�A�u�Ïؔԍ���null�v�̗�O���X���[����B<BR>
     *�@@�@@class: WEB3BusinessLayerException<BR>
     *�@@�@@tag:   BUSINESS_ERROR_01090<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
       final String STR_METHOD_NAME ="validate()";
       log.entering(STR_METHOD_NAME);
       
       // �P�j�O�������s��A���󋵈ꗗ�`�F�b�N
       // �P�|�P�jthis.�O�������s��A���󋵈ꗗ == null�̏ꍇ�A��O���X���[����B
       if (this.feqMarketLinkStateList == null)
       {
           log.debug("�O�������s��A���󋵈ꗗ�����w��ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_02650,
               this.getClass().getName() + STR_METHOD_NAME,
               "�O�������s��A���󋵈ꗗ�����w��ł��B" + this.feqMarketLinkStateList);      
       }
       
       // �P�|�Q�jthis.�O�������s��A���󋵈ꗗ�̌������A�ȉ��̏������J��Ԃ��B
       // �P�|�Q�|�P�j�O�������s��A����.validate()���R�[������B
       int l_intFeqMarketLinkStateListLength = this.feqMarketLinkStateList.length;
       for (int i = 0; i < l_intFeqMarketLinkStateListLength; i++)
       {
           this.feqMarketLinkStateList[i].validate();
       }
       
       // �Q�j�Ïؔԍ��`�F�b�N
       // this.�Ïؔԍ� == null�̏ꍇ�A�u�Ïؔԍ���null�v�̗�O���X���[����B
       if (this.password == null)
       {
           log.debug("�Ïؔԍ������w��ł��B");
           log.exiting(STR_METHOD_NAME);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_01090,
               this.getClass().getName() + STR_METHOD_NAME,
               "�Ïؔԍ������w��ł��B" + this.password);      
       }
       log.exiting(STR_METHOD_NAME);
    }
   
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFeqMarketLinkChangeCompleteResponse(this);
    }
}
@
