package ru.rubiconepro.study.Modules.Test;

import android.os.Bundle;
import android.widget.Toast;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ru.rubiconepro.study.Modules.Base.Layout.BaseLayout;
import ru.rubiconepro.study.R;

public class Test extends BaseLayout {

    public interface TestInterface {
        void a();
        void b();
    }

    public class FileWrite implements  TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    public class NetworkWrite implements TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    public class PipeWrite implements TestInterface {
        @Override
        public void a() {}
        @Override
        public void b() {}
    }

    void write (TestInterface writer) {
        writer.a();
    }


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        write(new NetworkWrite());
        write(new FileWrite());
    }
}
