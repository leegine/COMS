head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoDesignateEmbeddedNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨OP�w�薄�ʒm�T�[�r�X(WEB3IfoDesignateEmbeddedNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/16 ����� (���u) �V�K�쐬
*/

package webbroker3.ifo.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;

/**
 * (�敨OP�w�薄�ʒm�T�[�r�X)<BR>
 * �敨OP�w�薄�ʒm�T�[�r�X�C���^�t�F�C�X<BR>
 * @@author �����(���u)
 * @@version 1.0
 */
public interface WEB3IfoDesignateEmbeddedNotifyService extends Service 
{
    
    /**
     * (create�w�薄�ʒm)<BR>
     * <BR>
     * �w�薄�ʒm�L���[�e�[�u���ɍs���쐬����B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408CC9D0017F
     */
    public void createDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException;
    
    /**
     * (undo�w�薄�ʒm)<BR>
     * <BR>
     * �w�薄�ʒm�L���[�e�[�u���̍s�𖳌��ɂ���B<BR>
     * @@param l_orderUnit - �����P�ʃI�u�W�F�N�g
     * @@roseuid 408F1A2A0270
     */
    public void undoDesignateEmbeddedNotify(OrderUnit l_orderUnit) throws WEB3BaseException;
}
@
