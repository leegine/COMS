head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.07.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF������t���N�G�X�g(WEB3MutualFrgnMmfOrderAcceptRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �����F (���u) �V�K�쐬 (���f��536)
*/
package webbroker3.mf.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�O��MMF������t���N�G�X�g)
 * �O��MMF������t���N�G�X�g�N���X
 * 
 * @@author �����F(���u)
 * @@version 1.0
 */
public class WEB3MutualFrgnMmfOrderAcceptRequest extends WEB3BackRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "mutual_frgn_mmf_order_accept";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200702061500L;

    /**
     * (�O��MMF������t���N�G�X�g)<BR>
     * �f�t�H���g�R���X�g���N�^<BR>
     * @@roseuid 45B46BCA01A7
     */
    public WEB3MutualFrgnMmfOrderAcceptRequest() 
    {

    }

    /**
     * (create���X�|���X)<BR>
     * �icreateResponse�̎����j<BR>
     * <BR>
     * �O��MMF������t���X�|���X�I�u�W�F�N�g�𐶐����ĕԂ��B <BR>
     * @@return WEB3BackResponse
     * @@roseuid 45B46BE603BA
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3MutualFrgnMmfOrderAcceptResponse(this);
    }
}
@
