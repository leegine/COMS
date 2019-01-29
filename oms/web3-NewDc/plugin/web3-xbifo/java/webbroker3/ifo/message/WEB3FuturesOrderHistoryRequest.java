head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.20.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOrderHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨���������Ɖ�N�G�X�g�N���X(WEB3FuturesOrderHistoryRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 Ḗ@@�� (���u) �V�K�쐬
              001: 2004/08/06 ���Ō� (���u) Review�C��
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���敨���������Ɖ�N�G�X�g)<BR>
 * �����w���敨���������Ɖ�N�G�X�g�N���X
 * @@author Ḗ@@��
 * @@version 1.0
 */
public class WEB3FuturesOrderHistoryRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE ="futures_orderHistory";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221002L;        
    /**
     * (�����h�c)
     */
    public String id;
    
    /**
     * @@roseuid 40F7AE0D00CB
     */
    public WEB3FuturesOrderHistoryRequest() 
    {
     
    }
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�h�c�`�F�b�N<BR>
     * �@@this.�h�c��null�̒l�ł���Η�O���X���[����B<BR>
     *    class: WEB3BusinessLayerException<BR>
     *    tag:   BUSINESS_ERROR_00080<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A21FFC00A7
     */
    public void validate() throws WEB3BaseException 
    {
        if (WEB3StringTypeUtility.isEmpty(this.id))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00080, 
                getClass().getName() + "validate",
                "�h�c��null�̒l�ł���B");
        }
    }
    
    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE0D00EA
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3FuturesOrderHistoryResponse(this);
    }

 
}
@
