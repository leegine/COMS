head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.49.58;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3TrialCalcPortfolioService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X(WEB3TrialCalcPortfolioService.java)
Author Name      : Daiwa Institute of Research
*/
package webbroker3.trialcalc.service.delegate;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.service.delegate.WEB3BusinessService;

/**
 * �i�v�Z�T�[�r�X�|�[�g�t�H���I�T�[�r�X�j<BR>
 * <BR>
 * �|�[�g�t�H���I�T�[�r�X�C���^�t�F�[�X�B<BR>
 * <BR>
 * WEB3TrialCalcPortfolioService<BR>
 * @@author Dixit Deepankar
 * @@version 1.0
 */
public interface WEB3TrialCalcPortfolioService extends WEB3BusinessService
{
    /**
     * �|�[�g�t�H���I�T�[�r�X���������{����B<BR>
     * <BR>
     * ----<English>--------------------<BR>
     * <BR>
     * The portfolio service processing is executed. <BR>
     * @@param l_request - ���N�G�X�g�f�[�^�B
     * @@return WEB3GenResponse
     * @@throws WEB3BaseException WEB3BaseException
     * @@roseuid 4193329202C1
     */
    public WEB3GenResponse execute(WEB3GenRequest l_request) throws WEB3BaseException;
}@
