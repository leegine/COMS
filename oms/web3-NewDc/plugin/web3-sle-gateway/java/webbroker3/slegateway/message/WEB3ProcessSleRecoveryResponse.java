head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.07.01.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5744d7dbcbd3406;
filename	WEB3ProcessSleRecoveryResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : WEB3ProcessSleRecoveryResponse�N���X
 Author Name      : Daiwa Institute of Research
 Revision History : 2006/05/26 ��(FLJ) �V�K�쐬
 */
package webbroker3.slegateway.message;

import java.util.Date;


import webbroker3.common.message.WEB3GenResponse;


/**
 * ���J�o���[�����̃��X�|���X�N���X�ł��B
 * 
 * @@author      ���iFLJ�j
 * @@version     V1.0  
 */
public class WEB3ProcessSleRecoveryResponse extends WEB3GenResponse
{

    /** ���̃��b�Z�[�W��PTYPE�ł��B */
    public static final String PTYPE = "market.adapter.sle.process_recovery";
    
    /**
     * ���X�|���X�ԐM����
     */
    public Date date;
   
}
@
