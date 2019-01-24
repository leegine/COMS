/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : DOTBizDateProvider(DOTBizDateProvider.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/04/12 �V���@�h�O (FLJ) �V�K�쐬
*/
package jp.co.dir.dot.intellioms.rulesys;

import com.fitechlabs.fin.intellioms.rulesys.BizDateProvider;
import com.fitechlabs.fin.intellioms.util.Startable;

/**
 * DOTBizDateProvider
 * 
 * @author Eizo Saito (FLJ)
 * @version 1.0
 */
public interface DOTBizDateProvider extends BizDateProvider, Startable
{
    public static final String SHIFT_SYSTEM_TIMESTAMP = "shift.system.timestamp";

    public static final String SHIFT_SYSTEM_TIMESTAMP_MILLISECS = "shift.system.timestamp.millisecs";
    
    public static final String SYSTEM_BIZDATE = "system.bizdate";

    /**
     * get�r�W�l�X���t <BR>
     * <BR>
     * �r�W�l�X���t���擾����B <BR>
     * <BR>
     * @return String
     */
    public String getBizDate();
}
