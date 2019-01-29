head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesProductSelectRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�V�K�����������I����ʃ��N�G�X�g�N���X(WEB3FuturesProductSelectRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/20 ������ (���u) �V�K�쐬
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;

/**
 * (�����w���敨�V�K�����������I����ʃ��N�G�X�g)<BR>
 * �����w���敨�V�K�����������I����ʃ��N�G�X�g�N���X
 * @@author ������
 * @@version 1.0 
 */
public class WEB3FuturesProductSelectRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "futures_ProductSelect";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407201855L;

    /**
     * (���敪)<BR>
     * 1�F�����@@2�F����<BR>
     */
    public String contractType;

    /**
     * @@roseuid 40F7AE0D001F
     */
    public WEB3FuturesProductSelectRequest()
    {

    }

    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���ŊȌ�����ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@���敪�`�F�b�N<BR>
     * �@@�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00263<BR>
     * �@@�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B<BR>
     * �@@�@@�@@�@@�E�h1�F�����h<BR>
     * �@@�@@�@@�@@�E�h2�F�����h<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00264<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A218530140
     */
    public void validate() throws WEB3BaseException
    {
        //(�P�j�@@���敪�`�F�b�N)
        //�P�|�P�jthis.���敪��null�̒l�ł���Η�O���X���[����B
        if (this.contractType == null)
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00263, 
                this.getClass().getName() + "validate",
                "���敪��null�ł���");
        }
        //�P�|�Q�jthis.���敪���ȉ��̒l�ȊO�̏ꍇ��O���X���[����B
        if (!WEB3IfoContractTypeDef.OPEN_BUY.equals(this.contractType) 
            && !WEB3IfoContractTypeDef.OPEN_SELL.equals(this.contractType))
        {
            //��O
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00264, 
                this.getClass().getName() + "validate",
                "���敪���h1�F�����h�A�h2�F�����h�ȊO�ł���");
        }
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE0D003E
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesProductSelectResponse(this);
    }
}
@
