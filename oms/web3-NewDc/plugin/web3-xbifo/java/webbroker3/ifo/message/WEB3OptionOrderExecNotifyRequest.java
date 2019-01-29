head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionOrderExecNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����w���I�v�V�����o���ʒm���N�G�X�g
(WEB3OptionOrderExecNotifyRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 ���� (���u) �V�K�쐬
*/

package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (�����w���I�v�V�����o���ʒm���N�G�X�g)<BR>
 * �����w���I�v�V�����o���ʒm���N�G�X�g�N���X<BR>                                                                    
 * @@author ����
 * @@version 1.0
 */
public class WEB3OptionOrderExecNotifyRequest extends WEB3BackRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "options_orderExecNotify";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200406111835L;
    
    /**
     * ���ʃR�[�h�v���t�B�N�X�ꗗ<BR>
     */
    public String[] orderRequestNumberPrefixGroup;

    /**
     * �X���b�hNo<BR>
     */
    public Long threadNo;

    /**
     * @@roseuid 40C0AE49035B
     */
    public WEB3OptionOrderExecNotifyRequest() 
    {
     
    }
    
    /**
     * ���Y���N�G�X�g�ɑΉ����郌�X�|���X�I�u�W�F�N�g��Ԃ��B<BR>
     *<BR>
     * @@return ���X�|���X�I�u�W�F�N�g
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3OptionOrderExecNotifyResponse(this);
    }
}
@
