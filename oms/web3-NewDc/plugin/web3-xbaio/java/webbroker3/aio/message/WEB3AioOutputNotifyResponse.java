head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �o�ɒʒm���X�|���X(WEB3AioOutputNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 ��O�� (���u) �V�K�쐬
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�o�ɒʒm���X�|���X) <BR>
 * �o�ɒʒm���X�|���X�N���X <BR>
 * 
 * @@author ��O��(���u)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyResponse extends WEB3BackResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "aio_output_notify";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
   
    /**
     * @@roseuid 41E780B201C5
     */
    public WEB3AioOutputNotifyResponse()
    {
    }
    
    /**
     * �R���X�g���N�^�B�����ŗ^����ꂽ���N�G�X�g�I�u�W�F�N�g�����<BR>
     * ���X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     *<BR>
     * @@param l_request ���N�G�X�g�I�u�W�F�N�g
     */
    protected WEB3AioOutputNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}@
