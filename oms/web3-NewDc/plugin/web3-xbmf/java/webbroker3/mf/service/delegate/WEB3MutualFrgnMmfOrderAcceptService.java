head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFrgnMmfOrderAcceptService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �O��MMF������t�T�[�r�X(WEB3MutualFrgnMmfOrderAcceptService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/06 �����F (���u) �V�K�쐬 (���f��534)
*/
package webbroker3.mf.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.common.service.delegate.WEB3BackBusinessService;

/**
 * (�O��MMF������t�T�[�r�X)<BR>
 * �O��MMF������t�T�[�r�X�C���^�[�t�F�C�X<BR>
 * 
 * @@author �����F(���u)
 * @@version 1.0
 */
public interface WEB3MutualFrgnMmfOrderAcceptService extends WEB3BackBusinessService
{

    /**
     * �O��MMF������t�T�[�r�X���������{����B<BR>
     * @@param l_request - ���N�G�X�g�f�[�^<BR>
     * @@return WEB3BackResponse
     * @@throws WEB3BaseException
     * @@roseuid 45B9C7B901C7
     */
    public WEB3BackResponse execute(WEB3BackRequest l_request) throws WEB3BaseException;
}
@
