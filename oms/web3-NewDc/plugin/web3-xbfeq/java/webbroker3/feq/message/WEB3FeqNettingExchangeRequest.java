head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.29.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O���N�G�X�g(WEB3FeqNettingExchangeRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/10 �����F (���u) �V�K�쐬 ���f��548
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O�������בփl�b�e�B���O���N�G�X�g)<BR>
 * �O�������בփl�b�e�B���O���N�G�X�g<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeRequest  extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_netting_exchange";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 201009081516L;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FeqNettingExchangeRequest()
    {

    }

    /**
     * (�،���ЃR�[�h)<BR>
     * �،���ЃR�[�h<BR>
     */
    public String institutionCode;

    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return WEB3BackResponse
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3FeqNettingExchangeResponse(this);
    }
}
@
