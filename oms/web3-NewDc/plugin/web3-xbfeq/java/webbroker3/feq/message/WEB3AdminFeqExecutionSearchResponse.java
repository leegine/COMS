head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminFeqExecutionSearchResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �Ǘ��ҊO�������o�����͌������X�|���X(WEB3AdminFeqExecutionSearchResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 �Ջ`�g (���u) �V�K�쐬
                 : 2005/08/01 �s�p(���u) ���r���[
*/

package webbroker3.feq.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (�Ǘ��ҊO�������o�����͌������X�|���X)<BR>
 * �Ǘ��ҊO�������o�����͌������X�|���X
 *   
 * @@author �Ջ`�g
 * @@version 1.0
 */
public class WEB3AdminFeqExecutionSearchResponse extends WEB3GenResponse 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_feq_executionSearch";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200507121517L;  
    
    /**
     * @@roseuid 42CE3A0202AF
     */
    public WEB3AdminFeqExecutionSearchResponse() 
    {
     
    }
    
    /**
     * �R���X�g���N�^�B<BR>
     * �w�肳�ꂽ���N�G�X�g�I�u�W�F�N�g�ɑ΂��郌�X�|���X�I�u�W�F�N�g�𐶐�����B<BR>
     * @@param l_request - ���N�G�X�g�I�u�W�F�N�g
     */
    public WEB3AdminFeqExecutionSearchResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }  
}
@
