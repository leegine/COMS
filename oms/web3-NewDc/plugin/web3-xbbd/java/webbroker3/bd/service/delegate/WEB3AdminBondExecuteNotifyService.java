head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondExecuteNotifyService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �����ʒm�T�[�r�X(WEB3AdminBondExecuteNotifyService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/8/16 �����(���u) �V�K�쐬         
*/

package webbroker3.bd.service.delegate;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondOrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.bd.WEB3AdminBondDefaultInterceptor;

/**
 * (�����ʒm�T�[�r�X)<BR>
 * �����ʒm�T�[�r�X�C���^�[�t�F�[�X
 * 
 * @@author �����
 * @@version 1.0
 */
public interface WEB3AdminBondExecuteNotifyService extends Service
{
    
    /**
     * (notify���)<BR>
     * notify���<BR>
     * <BR>
     * ��菈��������<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A01FD
     */
    public void notifyExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (undo���)<BR>
     * undo���<BR>
     * <BR>
     * ���������������<BR>
     * @@param l_bondOrderUnit - (�������P��)<BR>
     * �������P��<BR>
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A022C
     */
    public void undoExecute(BondOrderUnit l_bondOrderUnit, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (accept�V�K����)<BR>
     * accept�V�K���������s<BR>
     * <BR>
     * �V�K������t����������<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A026A
     */
    public void acceptNewOrder(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
    
    /**
     * (accept�������)<BR>
     * accept����������������s<BR>
     * <BR>
     * ���������t����������<BR>
     * @@param l_lngOrderId - (����ID)<BR>
     * ����ID<BR>
     * @@param l_adminBondDefaultInterceptor - (���Ǘ��҃f�t�H���g�C���^�Z�v�^)<BR>
     * ���Ǘ��҃f�t�H���g�C���^�Z�v�^<BR>
     * @@throws WEB3BaseException
     * @@roseuid 44D9961A0299
     */
    public void acceptOrderCancel(long l_lngOrderId, 
        WEB3AdminBondDefaultInterceptor l_adminBondDefaultInterceptor) throws WEB3BaseException ;
}
@
