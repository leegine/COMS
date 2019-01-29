head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFrontOrderCommonService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : (�t�����g�������ʃT�[�r�X) (WEB3AdminFrontOrderCommonService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17  �Ӑ� (���u) �d�l�ύX���f��No.118
*/
package webbroker3.eqtypeadmin.service.delegate;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Service;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontMarketNoticeHistoryUnit;

/**
 * (�Ǘ��҃t�����g�������ʃT�[�r�X)<BR>
 * <BR>
 * �Ǘ��҃t�����g�������ʃT�[�r�X�C���^�t�F�[�X<BR>
 * <BR>
 * WEB3AdminFrontOrderCommonService interface<BR>
 * <BR>
 * @@author SCS.Sato
 * @@version 1.0
 */
public interface WEB3AdminFrontOrderCommonService extends Service {   
     /**
      * �s��ʒm�Ǘ��e�[�u���̌����ɓK�����s��R�[�h���擾���A<BR>
      * �ԋp����B<BR>
      * @@param �،���ЃR�[�h - �،���ЃR�[�h�B<BR>
      * @@return tring[]<BR>
      * @@roseuid 42F7104D0060
      */
    public String[] getFindPossibleMarketCode(String l_strInstitutionCode) throws WEB3BusinessLayerException, WEB3SystemLayerException;
   
     /**
      * �t�����g�����s��R�[�h����A�t�����g����������敪�R�[�h���擾����B<BR>
      * @@param �t�����g�����s��R�[�h - �t�����g�����s��R�[�h�B<BR>
      * @@return String<BR>
      * @@roseuid 42F710A40139
      */
    public String getFrontOrderExchangeCode(String l_strConvertMarketCode);
   
     /**
      * �t�����g�����s��R�[�h����A�t�����g�����V�X�e���敪���擾����B<BR>
      * @@param �t�����g�����s��R�[�h - �t�����g�����s��R�[�h�B<BR>
      * @@return String<BR>
      * @@roseuid 42F711C40131
      */
    public String getFrontSystemDiv(String l_strConvertMarketCode);
   
     /**
      * �ʒm�����Q�ƈꗗ���쐬����B<BR>
      * @@param �s��ʒm�Ǘ��ꗗ - �s��ʒm�Ǘ��ꗗList�B<BR>
      * @@return �s��ʒm���𖾍�[]<BR>
      * @@roseuid 42F7126200DF
      */
    public WEB3AdminFrontMarketNoticeHistoryUnit[] createNoticeHistryRefList(List l_histList);
   
     /**
      * �����̎s��R�[�h�A�t�����g�����V�X�e���敪�R�[�h����A��ʕ\���p��<BR>
      * �s��R�[�h�ɕϊ����A�ԋp����B<BR>
      * @@param �s��R�[�h - �s��R�[�h�B<BR>
      * @@param �t�����g�����V�X�e���敪 - �t�����g�����V�X�e���敪�B<BR>
      * @@return String<BR>
      * @@roseuid 42F71618033C
      */
    public String getFrontOrderMarketCode(String l_strMarketCode, String l_strFrontSystemCode);
   
    /**
     * �S�c�Ɠ��O�܂ł̉c�Ɠ��ꗗ���擾���A�ԋp����B<BR>
     * @@return Date[]<BR>
     * @@roseuid 4303F05800A7
     */
    public Date[] getNoticeReceivedDateRef() throws WEB3SystemLayerException;
    
}
@
