head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : ���[���G���W���֒������M����������T�[�r�X(WEB3RlsConnectorService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/12 �V�� �h�O(FLJ) �V�K�쐬
*/
package webbroker3.rlsgateway.connector;

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.rlsgateway.data.OmsConOrderRequestRow;

/**
 *
 * ���[���G���W���֒������M����������T�[�r�X
 * @@author Eizo Saito (FLJ)
 * @@version 1.0
 */
public interface WEB3RlsConnectorService extends Service
{

    /**
     * ���[���G���W���֒����o�^����������B
     * 
     * @@param OmsConOrderRequestRow - ���N�G�X�g
     * @@param OrderUnit - �������e
     * @@throws WEB3BaseException
     */
    public void register(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;

    /**
     * ���[���G���W���֒�����������������B
     * 
     * @@param OmsConOrderRequestRow - ���N�G�X�g
     * @@param OrderUnit - �������e
     * @@throws WEB3BaseException
     */
    public void modify(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;

    /**
     * ���[���G���W���֒����������������B
     * 
     * @@param OmsConOrderRequestRow - ���N�G�X�g
     * @@param OrderUnit - �������e
     * @@throws WEB3BaseException
     */
    public void cancel(OmsConOrderRequestRow l_request, OrderUnit l_unit) throws WEB3BaseException;
    
    /**
     * xTier�N���ς݃t���O��ݒ肷��B
     * 
     * @@param boolean - xTier�N���ς݃t���O
     */
    public void setIsXtierStarted(boolean l_isXtierStarted);
    
    /**
     * �R�l�N�^����������B
     * 
     */
    public void prepareConnection2Rls() throws WEB3SystemLayerException;
}
@
