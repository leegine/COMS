head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.24.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqNettingExchangeResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a�����r�W�l�X�E�C�m�x�[�V����
File Name        : �O�������בփl�b�e�B���O���X�|���X(WEB3FeqNettingExchangeResponse.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/09/10 �����F (���u) �V�K�쐬 ���f��548
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O�������בփl�b�e�B���O���X�|���X)<BR>
 * �O�������בփl�b�e�B���O���X�|���X<BR>
 * <BR>
 * @@author �����F
 * @@version 1.0
 */
public class WEB3FeqNettingExchangeResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "feq_netting_exchange";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 201009081517L;

    /**
     * �R���X�g���N�^�B<BR>
     */
    public WEB3FeqNettingExchangeResponse()
    {

    }

    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - (���N�G�X�g�I�u�W�F�N�g)<BR>
     */
    protected WEB3FeqNettingExchangeResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
