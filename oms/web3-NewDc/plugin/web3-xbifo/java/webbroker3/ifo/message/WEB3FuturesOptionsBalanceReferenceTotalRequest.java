head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.21.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceTotalRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���敨�I�v�V�����c���Ɖ�c�����v���N�G�X�g�N���X(WEB3FuturesOptionsBalanceReferenceTotalRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 ������ �V�K�쐬         
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (�����w���敨�I�v�V�����c���Ɖ�c�����v���N�G�X�g)<BR>
 * �����w���敨�I�v�V�����c���Ɖ�c�����v���N�G�X�g�N���X<BR>
 * @@author ������
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceTotalRequest extends WEB3GenRequest
{
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReferenceTotal";
    
    /**
     * �敨�^�I�v�V�����敪<BR>
     * �@@1�F�敨 2�F�I�v�V����<BR>
     */
    public String fuOpDiv;
    
    /**
     * �����N�G�X�g�f�[�^�̐������`�F�b�N���s���B<BR>
     * �i�������A���N���X���Ŋ�������ȈՃ`�F�b�N�݂̂Ƃ���B�j<BR>
     * <BR>
     * �P�j�@@�敨�^�I�v�V�����敪�̃`�F�b�N<BR>
     * �@@�P�|�P�jnull�̏ꍇ�A��O�Ƃ���B<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01736<BR>
     * �@@�P�|�Q�j�ȉ��̍��ڈȊO�����݂����ꍇ�A��O�Ƃ���B<BR>
     * �@@�@@�@@�E1(�敨)<BR>
     * �@@�@@�@@�E2(�I�v�V����)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01737<BR>
     * @@throws 
     * �V�X�e�����ʁiweb3-common�j.(web3)�V�X�e�������N���X_common.WEB3BaseException
     * @@roseuid 41AD445301B6
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME =
            getClass().getName()
                + ".validate()";
         //�P�j�@@�敨�^�I�v�V�����敪�̃`�F�b�N
        if (WEB3StringTypeUtility.isEmpty(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01736,
                getClass().getName() + STR_METHOD_NAME);
        }
        //�P�|�Q�j�ȉ��̍��ڈȊO�����݂����ꍇ�A��O�Ƃ���B
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv) && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                getClass().getName() + STR_METHOD_NAME);
        }
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesOptionsBalanceReferenceTotalResponse(this);
    }
}
@
